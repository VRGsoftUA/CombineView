package com.vrg.combineView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.vrg.combineView.common.Direction
import com.vrg.combineView.common.Type
import com.vrg.flipview.R

class MaskedView : AppCompatImageView {
    private var corners: Float = 0f
    private lateinit var type: Type
    private lateinit var direction: Direction
    private var cornersPath = Path()
    private var directionPath = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attr = context!!.theme.obtainStyledAttributes(attrs, R.styleable.MaskedView, 0, 0)
        try {
            corners = attr.getDimension(R.styleable.MaskedView_mvCorners, 0f)
            val id = attr.getInt(R.styleable.MaskedView_mvType, 1)
            type = Type.fromId(id)
            val dir = attr.getInt(R.styleable.MaskedView_mvDirection, 1)
            direction = Direction.fromId(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            attr.recycle()
        }
    }

    fun setType(type: Type) {
        this.type = type
        invalidate()
    }

    fun setDirection(direction: Direction) {
        this.direction = direction
        invalidate()
    }

    fun setCorners(corners: Float) {
        this.corners = corners
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cornersPath = Path()
        directionPath = Path()

        cornersPath.applyCorners(w, h)
        when (type) {
            Type.TWO -> {
                when (direction) {
                    Direction.TOP,
                    Direction.LEFT -> directionPath.applyTopLeft(w, h)
                    Direction.BOTTOM,
                    Direction.RIGHT -> directionPath.applyBottomRight(w, h)
                }
            }
            Type.FOUR -> {
                when (direction) {
                    Direction.TOP -> directionPath.applyTop(w, h)
                    Direction.BOTTOM -> directionPath.applyBottom(w, h)
                    Direction.LEFT -> directionPath.applyLeft(w, h)
                    Direction.RIGHT -> directionPath.applyRight(w, h)
                }
            }
        }

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.clipPath(cornersPath)
        canvas?.clipPath(directionPath)

        super.onDraw(canvas)
    }

    private fun Path.applyCorners(w: Int, h: Int) {
        val radii = FloatArray(8)

        radii[0] = corners
        radii[1] = corners

        radii[2] = corners
        radii[3] = corners

        radii[4] = corners
        radii[5] = corners

        radii[6] = corners
        radii[7] = corners

        this.addRoundRect(RectF(0f, 0f, w.toFloat(), h.toFloat()), radii, Path.Direction.CW)
    }

    private fun Path.applyTopLeft(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(0f, 0f)
        triangle.lineTo(w.toFloat(), 0f)
        triangle.lineTo(0f, h.toFloat())
        triangle.close()

        this.addPath(triangle)
    }

    private fun Path.applyBottomRight(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(w.toFloat(), h.toFloat())
        triangle.lineTo(w.toFloat(), 0f)
        triangle.lineTo(0f, h.toFloat())
        triangle.close()

        this.addPath(triangle)
    }

    private fun Path.applyTop(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(0f, 0f)
        triangle.lineTo(w.toFloat(), 0f)
        triangle.lineTo(w / 2f, h / 2f)
        triangle.close()

        this.addPath(triangle)
    }

    private fun Path.applyLeft(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(0f, 0f)
        triangle.lineTo(0f, h.toFloat())
        triangle.lineTo(w / 2f, h / 2f)
        triangle.close()

        this.addPath(triangle)
    }

    private fun Path.applyBottom(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(0f, h.toFloat())
        triangle.lineTo(w.toFloat(), h.toFloat())
        triangle.lineTo(w / 2f, h / 2f)
        triangle.close()

        this.addPath(triangle)
    }

    private fun Path.applyRight(w: Int, h: Int) {
        val triangle = Path()
        triangle.moveTo(w.toFloat(), 0f)
        triangle.lineTo(w.toFloat(), h.toFloat())
        triangle.lineTo(w / 2f, h / 2f)
        triangle.close()

        this.addPath(triangle)
    }
}