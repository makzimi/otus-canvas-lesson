package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import ru.otus.canvas.finish.Data.createLargeData

class StockChartView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    data class StockPrice (
        val low: Double,
        val high: Double,
    )

    private data class StockPriceInternal (
        val low: Double,
        val high: Double,
        val mid: Double,
    )

    private val list = ArrayList<StockPriceInternal>()
    private var maxValue = 0.0
    private var minValue = 0.0

    init {
        list.clear()
        list.addAll(
            createLargeData().map {
                StockPriceInternal(
                    low = it.low,
                    high = it.high,
                    mid = (it.high + it.low) / 2,
                )
            }
        )
        maxValue = list.maxOf { it.high }
        minValue = list.minOf { it.low }
    }


    private val paint = Paint().apply {
        color = Color.parseColor("#bd7ebe")
        strokeWidth = 8f
        style = Paint.Style.STROKE
        pathEffect = CornerPathEffect(60f)
    }

    private val redPaint = Paint().apply {
        color = Color.parseColor("#b30000")
        style = Paint.Style.STROKE
        strokeWidth = 16f
    }
    private val greenPaint = Paint().apply {
        color = Color.parseColor("#5ad45a")
        style = Paint.Style.STROKE
        strokeWidth = 16f
    }
    private val path = Path()
    private val upBarsPath = Path()
    private val downBarsPath = Path()

    private var scale = 1f

    override fun onDraw(canvas: Canvas) {
        //val wStep = measuredWidth.toFloat() / list.size.toFloat()
        val hStep = measuredHeight / (maxValue - minValue)

        val newSize = Integer.min(list.size, (list.size / scale).toInt())
        val first = Integer.max(0, (list.size - newSize) / 2)

        val wStep = measuredWidth.toFloat() / newSize.toFloat()

        path.reset()
        path.moveTo(0f, 0f)
        upBarsPath.reset()
        upBarsPath.moveTo(0f, 0f)
        downBarsPath.reset()
        downBarsPath.moveTo(0f, 0f)
        var x: Float = 0f
        var y: Float = 0f
        var yLow: Float = 0f
        var yHigh: Float = 0f
//        list.onEach {
//            y = ((it.high - minValue) * hStep).toFloat()
//            path.lineTo(x, y)
//            x += wStep
//        }
        for (i in first until first + newSize) {
            val item = list[i]
            y = ((item.mid - minValue) * hStep).toFloat()
            yLow = ((item.low - minValue) * hStep).toFloat()
            yHigh = ((item.high - minValue) * hStep).toFloat()
            path.lineTo(x, y)
            if (newSize < 50) {
                if (item.high > item.low) {
                    upBarsPath.moveTo(x, yLow)
                    upBarsPath.lineTo(x, yHigh)
                } else {
                    downBarsPath.moveTo(x, yLow)
                    downBarsPath.lineTo(x, yHigh)
                }
            }

            x += wStep
        }

        //canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawPath(path, paint)
        canvas.drawPath(upBarsPath, greenPaint)
        canvas.drawPath(downBarsPath, redPaint)
    }

    private val scaleGestureDetector = ScaleGestureDetector(context, object : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor

            invalidate()

            return true
        }
    })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }
}