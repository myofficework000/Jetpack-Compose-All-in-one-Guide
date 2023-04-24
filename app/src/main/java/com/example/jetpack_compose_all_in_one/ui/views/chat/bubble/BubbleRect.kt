package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

data class BubbleRect(
    var left: Float = 0f,
    var top: Float = 0f,
    var right: Float = 0f,
    var bottom: Float = 0f
) {

    fun set(left: Float, top: Float, right: Float, bottom: Float) {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    val height: Float
        get() {
            return bottom - top
        }

    val width: Float
        get() {
            return right - left
        }

    override fun toString(): String {
        return "left: $left, top: $top, right: $right, bottom: $bottom, " +
                "width: $width, height: $height"
    }
}