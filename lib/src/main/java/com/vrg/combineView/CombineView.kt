package com.vrg.combineView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.RequestBuilder
import com.vrg.combineView.common.Direction
import com.vrg.combineView.common.Type
import com.vrg.flipview.R

class CombineView : ConstraintLayout {
    private var corners: Float = 0f
    private lateinit var type: Type

    private lateinit var top: MaskedView
    private lateinit var bottom: MaskedView
    private lateinit var left: MaskedView
    private lateinit var right: MaskedView

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attr = context!!.theme.obtainStyledAttributes(attrs, R.styleable.CombineView, 0, 0)
        try {
            corners = attr.getDimension(R.styleable.CombineView_cvCorners, 0f)
            val id = attr.getInt(R.styleable.CombineView_cvType, 1)
            type = Type.fromId(id)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            attr.recycle()
        }
    }

    //region Glide

    fun loadIntoTop(request: RequestBuilder<Drawable>){
        when(type){
            Type.TWO -> request.into(top)
            Type.FOUR -> request.into(top)
        }
    }

    fun loadIntoBottom(request: RequestBuilder<Drawable>){
        when(type){
            Type.TWO -> request.into(bottom)
            Type.FOUR -> request.into(bottom)
        }
    }

    fun loadIntoLeft(request: RequestBuilder<Drawable>){
        when(type){
            Type.TWO -> request.into(top)
            Type.FOUR -> request.into(left)
        }
    }

    fun loadIntoRight(request: RequestBuilder<Drawable>){
        when(type){
            Type.TWO -> request.into(bottom)
            Type.FOUR -> request.into(right)
        }
    }

    //endregion

    //region Drawable

    fun setTopImage(drawable: Drawable){
        when(type){
            Type.TWO -> top.setImageDrawable(drawable)
            Type.FOUR -> top.setImageDrawable(drawable)
        }
    }

    fun setBottomImage(drawable: Drawable){
        when(type){
            Type.TWO -> bottom.setImageDrawable(drawable)
            Type.FOUR -> bottom.setImageDrawable(drawable)
        }
    }

    fun setLeftImage(drawable: Drawable){
        when(type){
            Type.TWO -> top.setImageDrawable(drawable)
            Type.FOUR -> left.setImageDrawable(drawable)
        }
    }

    fun setRightImage(drawable: Drawable){
        when(type){
            Type.TWO -> bottom.setImageDrawable(drawable)
            Type.FOUR -> right.setImageDrawable(drawable)
        }
    }

    //endregion

    fun setType(type: Type){
        if(this.type == type){
            return
        }

        createView()
    }

    fun setCorners(value: Float){
        corners = value

        top.setCorners(corners)
        bottom.setCorners(corners)

        when(type){
            Type.FOUR -> {
                left.setCorners(corners)
                right.setCorners(corners)
            }
        }

    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        createView()
    }

    private fun createView() {
        removeAllViews()
        when (type) {
            Type.TWO -> {
                top = MaskedView(context)
                bottom = MaskedView(context)

                top.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.TWO)
                    setDirection(Direction.TOP)
                    setCorners(corners)
                }
                bottom.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.TWO)
                    setDirection(Direction.BOTTOM)
                    setCorners(corners)
                }

                addView(top)
                addView(bottom)

                top.setImageDrawable(ColorDrawable(Color.parseColor("#EDEDED")))
                bottom.setImageDrawable(ColorDrawable(Color.parseColor("#E0E0E0")))
            }
            Type.FOUR -> {
                top = MaskedView(context)
                bottom = MaskedView(context)
                left = MaskedView(context)
                right = MaskedView(context)

                top.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.FOUR)
                    setDirection(Direction.TOP)
                    setCorners(corners)
                }
                bottom.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.FOUR)
                    setDirection(Direction.BOTTOM)
                    setCorners(corners)
                }
                left.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.FOUR)
                    setDirection(Direction.LEFT)
                    setCorners(corners)
                }
                right.apply {
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    scaleType = ImageView.ScaleType.FIT_XY
                    setType(Type.FOUR)
                    setDirection(Direction.RIGHT)
                    setCorners(corners)
                }

                addView(top)
                addView(bottom)
                addView(left)
                addView(right)

                top.setImageDrawable(ColorDrawable(Color.parseColor("#EDEDED")))
                left.setImageDrawable(ColorDrawable(Color.parseColor("#E0E0E0")))
                bottom.setImageDrawable(ColorDrawable(Color.parseColor("#EDEDED")))
                right.setImageDrawable(ColorDrawable(Color.parseColor("#E0E0E0")))
            }
        }
    }
}