package ru.otus.canvas.start

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import ru.otus.canvas.start.Data.createLargeData

class StockChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    data class StockPrice(
        val open: Double,
        val close: Double,
    )

    private data class StockPriceInternal(
        val open: Double,
        val close: Double,
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
                    open = it.open,
                    close = it.close,
                    mid = (it.open + it.close) / 2,
                )
            }
        )
        maxValue = list.maxOf { it.close }
        minValue = list.minOf { it.open }
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

    override fun onDraw(canvas: Canvas) {

    }
}