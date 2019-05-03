package com.vrg.combineView.common

enum class Direction(private val id: Int) {
    TOP(1), BOTTOM(2), LEFT(3), RIGHT(4);

    companion object {
        fun fromId(id: Int): Direction {
            return when(id){
                1 -> TOP
                2 -> BOTTOM
                3 -> LEFT
                else -> RIGHT
            }
        }
    }
}