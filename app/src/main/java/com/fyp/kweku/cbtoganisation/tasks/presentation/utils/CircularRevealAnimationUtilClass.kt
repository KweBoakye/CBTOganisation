package com.fyp.kweku.cbtoganisation.tasks.presentation.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.view.View
import android.view.View.GONE
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlinx.android.parcel.Parcelize
import kotlin.math.sqrt


class CircularRevealAnimationUtilClass {

    interface AnimationFinishedListener{
        fun onAnimationFinished()
    }

    companion object{
        fun getMediumDuration(context:Context):Int{
            return context.resources.getInteger(android.R.integer.config_longAnimTime)

        }

      @ColorInt
      fun getColor(context: Context, @ColorRes colorid: Int):Int{
          return ContextCompat.getColor(context, colorid)
      }

        fun registerCircularRevealAnimation(context: Context, view: View, revealSettings: RevealAnimationSetting,startColor: Int, endColor:Int, listener: AnimationFinishedListener){
            view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onLayoutChange(
                    v: View,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    v.removeOnLayoutChangeListener(this)

                    val cx = revealSettings.centerX
                    val cy = revealSettings.centerY
                    val width = revealSettings.width
                    val height = revealSettings.height

                    // use the diagonal of the view
                    val finalRadius:Float = sqrt(((width * width) + (height * height)).toDouble()).toFloat()
                    val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius)
                    val duration:Int = getMediumDuration(context)
                    anim.duration = duration.toLong()
                    anim.interpolator =AccelerateInterpolator()
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            listener.onAnimationFinished()
                        }
                    })
                    anim.start()
                    startBackgroundColorAnimation(view, startColor, endColor, duration)
                }
            })
        }

         fun startCircularRevealExitAnimation(context: Context, view: View, revealSettings: RevealAnimationSetting,startColor: Int, endColor:Int, listener: AnimationFinishedListener){
            val cx = revealSettings.centerX
            val cy = revealSettings.centerY
            val width = revealSettings.width
            val height = revealSettings.height

            val initRadius:Float = sqrt(((width * width) + (height * height)).toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,initRadius , 0f)
            val duration:Int = getMediumDuration(context)
            anim.interpolator =AccelerateInterpolator()// FastOutSlowInInterpolator()
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = GONE
                    listener.onAnimationFinished()
                }
            })
            anim.start()
            startBackgroundColorAnimation(view,startColor, endColor, duration)
        }


        private fun startBackgroundColorAnimation(view: View, startColor: Int, endColor: Int, duration: Int) {
            val anim = ValueAnimator()
            anim.setIntValues(startColor, endColor)
            anim.setEvaluator(ArgbEvaluator())
            anim.duration = duration.toLong()
            anim.addUpdateListener { valueAnimator -> view.setBackgroundColor(valueAnimator.animatedValue as Int) }
            anim.start()
        }
    }

    @Parcelize
    data class RevealAnimationSetting(val centerX: Int,
                                      val centerY: Int,
                                      val width:Int,
                                      val height:Int) : Parcelable{

        companion object {

            fun with(centerX: Int, centerY: Int, width: Int, height: Int): RevealAnimationSetting {
                return RevealAnimationSetting(centerX, centerY, width, height)
            }
        }
    }
}