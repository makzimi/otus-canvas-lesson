package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TestMatrixCanvasView @JvmOverloads constructor (
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
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val path = Path()
    private val matrix = Matrix()

    override fun onDraw(canvas: Canvas) {
        val midHeight = height / 2f
        val midWidth = width / 2f

        path.reset()
        path.moveTo(midWidth, midHeight)
        path.lineTo(midWidth + 100, midHeight)
        path.lineTo(midWidth + 100, midHeight + 100)
        path.close()

        canvas.drawPath(path, greenStrokePaint)

        matrix.preTranslate(30f, 30f)
        path.transform(matrix)

        matrix.postRotate(45f, midWidth, midHeight)
        path.transform(matrix)

        matrix.setScale(1.5f, 1.2f, midWidth, midHeight)
        path.transform(matrix)

        canvas.drawPath(path, boldRedPaint)
    }
}