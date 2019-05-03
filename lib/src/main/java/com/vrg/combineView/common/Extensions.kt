package com.vrg.combineView.common

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.vrg.combineView.CombineView

fun RequestBuilder<Drawable>.intoTop(target: CombineView){
    target.loadIntoTop(this)
}

fun RequestBuilder<Drawable>.intoBottom(target: CombineView){
    target.loadIntoBottom(this)
}

fun RequestBuilder<Drawable>.intoLeft(target: CombineView){
    target.loadIntoLeft(this)
}

fun RequestBuilder<Drawable>.intoRight(target: CombineView){
    target.loadIntoRight(this)
}