package ru.otus.canvas.start

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import ru.otus.canvas.R

class AndroidGestureView  : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val androidIcon: Drawable =
        requireNotNull(ResourcesCompat.getDrawable(resources, R.drawable.ic_android_black_24dp, null))
            .also { it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight) }

    private var imgPosX = 0f
    private var imgPosY = 0f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        imgPosX = width / 2f
        imgPosY = height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}