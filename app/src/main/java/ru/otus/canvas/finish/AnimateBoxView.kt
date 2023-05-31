package ru.otus.canvas.finish

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator

class AnimateBoxView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val size = 200
    private var rotate = 0f
    private var scale = 1f

    init {
        setOnClickListener {
            startAnim()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val midHeight = height / 2f
        val midWidth = width / 2f

        canvas.scale(scale, scale, midWidth, midHeight)
        canvas.rotate(rotate, midWidth, midHeight)

        canvas.drawRect(midWidth - size / 2f, midHeight - size / 2f, midWidth + size / 2f, midHeight + size / 2f, paint)
    }

    private fun startAnim() {
        val scalePVHName = "scalePVHName"
        val scalePVH = PropertyValuesHolder.ofFloat(scalePVHName, 1f, 2f)

        val rotatePVHName = "rotatePVHName"
        val rotatePVH = PropertyValuesHolder.ofFloat(rotatePVHName, 0f, 360f)
        ValueAnimator.ofPropertyValuesHolder(scalePVH, rotatePVH).apply {
            duration = 3000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            interpolator = BounceInterpolator()
            addUpdateListener {
                rotate = it.getAnimatedValue(rotatePVHName) as Float
                scale = it.getAnimatedValue(scalePVHName) as Float
                invalidate()
            }
            start()
        }
    }
}