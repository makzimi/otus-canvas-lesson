package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class TestCanvasView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val blackPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        style = Paint.Style.FILL
    }

    private val greenStrokePaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val boldRedPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 30f
    }

    private val rect = RectF()

    override fun onDraw(canvas: Canvas) {
        // drawRBG
        canvas.drawRGB(123,123, 255)

        // drawLine
        canvas.drawLine(0f,0f, width / 2f, height / 2f, blackPaint)

        // drawPoint
        canvas.drawPoint(width / 2f + 50, height / 2f + 50, boldRedPaint)

        // drawCircle
        canvas.drawCircle(width / 2f - 50, height / 2f - 50, 250f, greenStrokePaint)

        // drawRoundRect
        canvas.drawRoundRect(0f,0f, width / 2f, height / 2f, 100f, 100f, greenStrokePaint)

        // drawOval
        canvas.drawOval(100f, 200f, 700f, 400f, greenStrokePaint)

        // drawArc
        canvas.drawArc(200f, 600f, 700f, 1000f, 45f, 65f, true, greenStrokePaint)

        // drawArc with RectF()
        rect.set(100f, 1200f, 400f, 1500f)
        canvas.drawArc(rect, 0f, 90f, true, greenStrokePaint)
        canvas.drawArc(rect, 90f, 90f, true, greenStrokePaint)
        canvas.drawArc(rect, 180f, 90f, true, greenStrokePaint)

        // rect offset
        rect.offset(30f, -30f)
        canvas.drawArc(rect, 270f, 90f, true, greenStrokePaint)

        // drawText
        blackPaint.textSize = 200f
        blackPaint.textAlign = Paint.Align.LEFT
        canvas.drawText("Hello", 50f, 200f, blackPaint)
        val textWidth = blackPaint.measureText("Hello Ivan!")
        println("textWidth = $textWidth")
    }
}