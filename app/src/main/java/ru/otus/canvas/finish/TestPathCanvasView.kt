package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Path.Direction.CCW
import android.graphics.Path.Direction.CW
import android.graphics.PathDashPathEffect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class TestPathCanvasView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val blackPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 30f
        style = Paint.Style.FILL
    }

    private val greenStrokePaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val boldRedPaint = Paint().apply {
        color = Color.RED
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val path = Path()
    private val path2 = Path()

    override fun onDraw(canvas: Canvas) {
        val midW = width / 2
        val midH = height / 2

        path.reset()
        path.moveTo(0f, 0f)
        path.lineTo(midW - 200f, 200f)
        path.lineTo(midW + 200f, height - 200f)
        path.lineTo(width.toFloat(), height.toFloat())
        path.quadTo(120f, 120f, 20f, 40f)
        path.cubicTo(120f, 120f, 40f, 80f, 50f, 90f)
        path.moveTo(midW - 200f, midH - 400f)
        path.lineTo(midW - 100f, midH + 600f)
        path.lineTo(midW + 100f, midH - 200f)
        path.close()
        path.addRect(300f, 300f, 600f, 600f, CW)
        path.addOval(500f, 500f, 900f, 900f, CW)
        greenStrokePaint.pathEffect = CornerPathEffect(300f)
        canvas.drawPath(path, greenStrokePaint)

        path2.reset()
        path2.moveTo(0f, 0f)
        path2.quadTo(width.toFloat(), midH / 2f, midW.toFloat(), midH.toFloat())

        path2.cubicTo(
            midW + 600f, 200f,
            midW + 600f, midH - 200f,
            midW.toFloat(), midH.toFloat(),
        )
        path2.cubicTo(
            midW - 600f, midH + 200f,
            midW - 600f, height - 200f,
            width.toFloat(), height.toFloat(),
        )

        boldRedPaint.pathEffect = DashPathEffect(floatArrayOf(10f, 20f), 0f)
        boldRedPaint.pathEffect = PathDashPathEffect(path, 100f, 0f, PathDashPathEffect.Style.TRANSLATE)
        canvas.drawPath(path2, boldRedPaint)

        canvas.drawPoint(width.toFloat(), midH / 2f, blackPaint)
        canvas.drawPoint(midW.toFloat(), midH.toFloat(), blackPaint)
    }
}