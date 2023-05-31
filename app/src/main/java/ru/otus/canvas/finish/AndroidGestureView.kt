package ru.otus.canvas.finish

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.core.content.res.ResourcesCompat
import ru.otus.canvas.R

class AndroidGestureView  : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val scaleGestureDetector = ScaleGestureDetector(context, object : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scale *= detector.scaleFactor

            invalidate()

            return true
        }
    })

    private val generalGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            scale *= 1.1f
            invalidate()
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            scale *= 0.9f
            invalidate()
            return true
        }
    })

    private val androidIcon: Drawable =
        requireNotNull(ResourcesCompat.getDrawable(resources, R.drawable.ic_android_black_24dp, null))
            .also { it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight) }

    private var imgPosX = 0f
    private var imgPosY = 0f
    private var scale = 1f

    private var lastTouchX = 0f
    private var lastTouchY = 0f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        imgPosX = width / 2f
        imgPosY = height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.scale(scale, scale, imgPosX - androidIcon.intrinsicWidth / 2, imgPosY - androidIcon.intrinsicHeight / 2)
        canvas.translate(imgPosX - androidIcon.intrinsicWidth / 2, imgPosY - androidIcon.intrinsicHeight / 2)
        androidIcon.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //scaleGestureDetector.onTouchEvent(event)
        generalGestureDetector.onTouchEvent(event)

//        if (event.action == MotionEvent.ACTION_DOWN) {
//            lastTouchX = event.x
//            lastTouchY = event.y
//        } else if (event.action == MotionEvent.ACTION_MOVE) {
//            val dx = event.x - lastTouchX
//            val dy = event.y - lastTouchY
//
//            imgPosX += dx
//            imgPosY += dy
//
//            lastTouchX = event.x
//            lastTouchY = event.y
//
//            invalidate()
//        }

        return true
    }
}