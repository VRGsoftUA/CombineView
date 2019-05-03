package com.vrg.combineView.common

enum class Type(private val id: Int) {
    TWO(1), FOUR(2);

    companion object {
        fun fromId(id: Int): Type {
            return when(id){
                1 -> TWO
                else -> FOUR
            }
        }
    }
}