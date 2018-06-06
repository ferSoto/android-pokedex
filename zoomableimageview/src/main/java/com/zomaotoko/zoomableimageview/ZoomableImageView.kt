package com.zomaotoko.zoomableimageview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

class ZoomableImageView : ImageView {
    companion object {
        const val ANIMATION_DURATION = 240L
    }

    lateinit var target: ImageView
    private var animatorSet: AnimatorSet? = null
    private var isZoomed = false

    init {
        setOnClickListener { zoomIn() }
    }

    private fun zoomIn() {
        if (isZoomed) return
        isZoomed = true
        animatorSet?.cancel()

        val startRect = getThisRect()
        val (finalRect, offset) = getRectWithOffset()
        startRect.adjustOffset(offset)
        finalRect.adjustOffset(offset)

        val startScale = if (finalRect.ratio > startRect.ratio) {
            startRect.scaleOnWidth(finalRect)
        } else {
            startRect.scaleOnHeight(finalRect)
        }

        target.let {
            it.background = this.background
            it.setOnClickListener { zoomOut(startRect, startScale) }
            it.visibility = View.VISIBLE
            it.pivotX = 0f
            it.pivotY = 0f
        }

        runZoomInAnimation(startRect, finalRect, startScale)
    }

    private fun runZoomInAnimation(startRect: Rect, finalRect: Rect, startScale: Float) {
        animatorSet = AnimatorSet()
        animatorSet?.let {
            it.play(ObjectAnimator.ofFloat(target, View.X, startRect.left.toFloat(), finalRect.left.toFloat()))
                    .with(ObjectAnimator.ofFloat(target, View.Y, startRect.top.toFloat(), finalRect.top.toFloat()))
                    .with(ObjectAnimator.ofFloat(target, View.SCALE_X, startScale, 1f))
                    .with(ObjectAnimator.ofFloat(target, View.SCALE_Y, startScale, 1f))
            it.duration = ANIMATION_DURATION
            it.interpolator = DecelerateInterpolator()
            it.addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    animatorSet = null
                }

                override fun onAnimationCancel(animation: Animator?) {
                    animatorSet = null
                }
            })
            it.start()
        }
    }

    private fun zoomOut(startRect: Rect, startScale: Float) {
        if (!isZoomed) return
        isZoomed = false

        runZoomOutAnimation(startRect, startScale)
    }

    private fun runZoomOutAnimation(startRect: Rect, startScale: Float) {
        fun animationEnd() {
            target.visibility = View.GONE
            animatorSet = null
        }

        animatorSet = AnimatorSet()
        animatorSet?.let {
            it.play(ObjectAnimator.ofFloat(target, View.X, startRect.left.toFloat()))
                    .with(ObjectAnimator.ofFloat(target, View.Y, startRect.top.toFloat()))
                    .with(ObjectAnimator.ofFloat(target, View.SCALE_X, startScale))
                    .with(ObjectAnimator.ofFloat(target, View.SCALE_Y, startScale))
            it.duration = ANIMATION_DURATION
            it.interpolator = DecelerateInterpolator()
            it.addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    animationEnd()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    animationEnd()
                }
            })
            it.start()
        }
    }

    private fun getThisRect() : Rect {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        return rect
    }

    private fun getRectWithOffset() : Pair<Rect, Point> {
        val rect = Rect()
        val offset = Point()
        target.getGlobalVisibleRect(rect, offset)
        return Pair(rect, offset)
    }


    // Extensions

    private val Rect.ratio : Float
        get() = width().toFloat() / height().toFloat()

    private fun Rect.scaleOnWidth(final: Rect) : Float {
        val scale = heightScale(final)
        val startWidth = scale * final.width()
        val delta = ((startWidth - width()) / 2).toInt()
        left -= delta
        right += delta
        return scale
    }

    private fun Rect.scaleOnHeight(final: Rect) : Float {
        val scale = widthScale(final)
        val startHeight = scale * final.height()
        val delta = ((startHeight - height()) / 2).toInt()
        top -= delta
        bottom += delta
        return scale
    }

    private fun Rect.widthScale(rect: Rect) = width().toFloat() / rect.width().toFloat()
    private fun Rect.heightScale(rect: Rect) = height().toFloat() / rect.height().toFloat()

    private fun Rect.adjustOffset(point: Point) {
        offset(-point.x, -point.y)
    }


    // Constructors (all empty but required)

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
    }
}