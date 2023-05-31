package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.otus.canvas.R

class BadgeView : AppCompatTextView {

    companion object {
        private const val DEFAULT_ANGLE: Int = 0
        private const val DEFAULT_COLOR: Int = Color.TRANSPARENT
    }

    private var badgeRect = RectF()
    private val badgePaint = Paint()
    private var badgeColor: Int = DEFAULT_COLOR
    private var badgeAngle: Float = DEFAULT_ANGLE.toFloat()

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attributeSet: AttributeSet,
    ) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
    ) : super(context, attributeSet, defStyleAttr) {
        init(context, attributeSet)
    }

    private fun init(
        context: Context,
        attributeSet: AttributeSet,
    ) {
        parseAttributes(context, attributeSet)
        badgePaint.isAntiAlias = true
        changeColor(badgeColor)
    }

    private fun changeColor(color: Int) {
        badgePaint.color = color
    }

    private fun parseAttributes(
        context: Context,
        attributeSet: AttributeSet,
    ) {
        context.obtainStyledAttributes(
            attributeSet, R.styleable.BadgeView, 0, 0
        )
            .apply {
                try {
                    badgeAngle =
                        getInt(R.styleable.BadgeView_badgeAngle, DEFAULT_ANGLE).toFloat()
                    badgeColor = getColor(R.styleable.BadgeView_badgeColor, DEFAULT_COLOR)
                } finally {
                    recycle()
                }
            }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            save()
            rotate(badgeAngle, measuredWidth.toFloat() / 2, measuredHeight.toFloat() / 2)
            val radius = measuredHeight / 2
            badgeRect.set(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
            drawRoundRect(badgeRect, radius.toFloat(), radius.toFloat(), badgePaint)

            super.onDraw(this)

            restore()
        }
    }
}
