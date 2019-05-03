package com.vrg.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vrg.combineView.common.intoBottom
import com.vrg.combineView.common.intoLeft
import com.vrg.combineView.common.intoRight
import com.vrg.combineView.common.intoTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        Glide.with(this)
                .load(R.drawable.elephant)
                .intoTop(imageTwo)

        Glide.with(this)
                .load(R.drawable.racoon)
                .intoBottom(imageTwo)


        Glide.with(this)
                .load(R.drawable.elephant)
                .intoTop(imageFour)

        Glide.with(this)
                .load(R.drawable.racoon)
                .intoBottom(imageFour)

        Glide.with(this)
                .load(R.drawable.panda)
                .intoLeft(imageFour)

        Glide.with(this)
                .load(R.drawable.tiger)
                .intoRight(imageFour)

        imageFour.setCorners(24f)

    }
}