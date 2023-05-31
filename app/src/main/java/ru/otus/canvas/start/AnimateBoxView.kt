package ru.otus.canvas.start

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class AnimateBoxView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {

    }
}