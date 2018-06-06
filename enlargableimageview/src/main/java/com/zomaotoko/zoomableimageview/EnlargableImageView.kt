package com.zomaotoko.zoomableimageview

import android.animation.*
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout

class EnlargableImageView : ImageView {
    companion object {
        const val ANIMATION_DURATION = 240L
    }

    private lateinit var enlargedInitialRect: Rect
    private var animatorSet: AnimatorSet? = null
    private var isEnlarged = false

    init {
        setOnClickListener { enlarge() }
    }

    var enlargedImageView: ImageView? = null
        set(imageView) {
            field = imageView
            imageView?.let{
                enlargedInitialRect = it.globalRect.clone
                it.visibility = GONE
            }
        }

    private fun enlarge() {
        if (isEnlarged) return
        isEnlarged = true
        animatorSet?.cancel()

        // Get rect for thumbnail and enlarged image.
        // The reference to enlarged image initial rect should be kept.
        // Also, move Y offset of thumbnail half height up.
        val startRect = this.globalRect
        val finalRect = enlargedInitialRect.clone
        startRect.offset(0, - startRect.height() / 2)

        val startScale = if (finalRect.ratio > startRect.ratio) {
            startRect.scaleOnWidth(finalRect)
        } else {
            startRect.scaleOnHeight(finalRect)
        }

        // Show enlarged image and replace on click listener
        enlargedImageView?.let {
            it.setImageDrawable(this.background)
            it.setOnClickListener { restore(startRect, startScale) }
            it.visibility = View.VISIBLE
            it.pivotX = 0f
            it.pivotY = 0f
        }

        runEnlargeAnimation(startRect, finalRect, startScale)
    }

    private fun runEnlargeAnimation(startRect: Rect, finalRect: Rect, startScale: Float) {
        enlargeImage(startRect, finalRect, startScale)
        animateBackground()
    }

    private fun enlargeImage(startRect: Rect, finalRect: Rect, startScale: Float) {
        animatorSet = AnimatorSet()
        animatorSet?.let {
            it.play(ObjectAnimator.ofFloat(enlargedImageView, View.X, startRect.left.toFloat(), finalRect.left.toFloat()))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.Y, startRect.top.toFloat(), finalRect.top.toFloat()))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.SCALE_X, startScale, 1f))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.SCALE_Y, startScale, 1f))
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

    private fun animateBackground() {
        (enlargedImageView?.parent as RelativeLayout).let { layout ->
            layout.visibility = View.VISIBLE
            ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, Color.parseColor("#77000000")).apply {
                duration = ANIMATION_DURATION
                addUpdateListener { animator ->
                    layout.setBackgroundColor(animator.animatedValue as Int)
                }
            }.start()

            layout.setOnClickListener {
                enlargedImageView?.callOnClick()
            }
        }
    }

    private fun restore(startRect: Rect, startScale: Float) {
        if (!isEnlarged) return
        isEnlarged = false
        runRestoreAnimation(startRect, startScale)
    }

    private fun runRestoreAnimation(startRect: Rect, startScale: Float) {
        restoreImage(startRect, startScale)
        restoreBackground()
    }

    private fun restoreImage(startRect: Rect, startScale: Float) {
        fun animationEnd() {
            with(enlargedImageView!!) {
                setImageResource(0)
                visibility = View.GONE
            }
            animatorSet = null
        }

        animatorSet = AnimatorSet()
        animatorSet?.let {
            it.play(ObjectAnimator.ofFloat(enlargedImageView, View.X, startRect.left.toFloat()))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.Y, startRect.top.toFloat()))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.SCALE_X, startScale))
                    .with(ObjectAnimator.ofFloat(enlargedImageView, View.SCALE_Y, startScale))
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

    private fun restoreBackground() {
        (enlargedImageView?.parent as RelativeLayout).let { layout ->
            ValueAnimator.ofObject(ArgbEvaluator(), Color.parseColor("#77000000"), Color.TRANSPARENT).apply {
                duration = ANIMATION_DURATION
                addUpdateListener { animator ->
                    layout.setBackgroundColor(animator.animatedValue as Int)
                }
                addListener(object: AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        layout.visibility = View.GONE
                    }
                })
            }.start()
            layout.setOnClickListener(null)
        }
    }


    // Extensions

    private val ImageView.globalRect: Rect
        get() {
            val rect = Rect()
            getGlobalVisibleRect(rect)
            return rect
        }

    private val Rect.ratio: Float
        get() = width().toFloat() / height().toFloat()

    private fun Rect.scaleOnWidth(final: Rect): Float {
        val scale = heightScale(final)
        val startWidth = scale * final.width()
        val delta = ((startWidth - width()) / 2).toInt()
        left -= delta
        right += delta
        return scale
    }

    private fun Rect.scaleOnHeight(final: Rect): Float {
        val scale = widthScale(final)
        val startHeight = scale * final.height()
        val delta = ((startHeight - height()) / 2).toInt()
        top -= delta
        bottom += delta
        return scale
    }

    private fun Rect.widthScale(rect: Rect) = width().toFloat() / rect.width().toFloat()
    private fun Rect.heightScale(rect: Rect) = height().toFloat() / rect.height().toFloat()

    private val Rect.clone: Rect
        get() = Rect(left, top, right, bottom)


    // Constructors (all empty but required)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)
}