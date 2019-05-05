package com.example.arkanoid

import android.graphics.RectF

class Brick(row: Int, column: Int, width: Int, height: Int) {

    private var rect: RectF

    private var visibility: Boolean = false

    init {

        visibility = true

        val padding = 1

        rect = RectF(
            (column * width + padding).toFloat(),
            (row * height + padding).toFloat(),
            (column * width + width - padding).toFloat(),
            (row * height + height - padding).toFloat()
        )
    }

    fun setInvisible() {
        visibility = false
    }

    fun getRect(): RectF {
        return this.rect
    }

    fun getVisibility(): Boolean {
        return visibility
    }
}