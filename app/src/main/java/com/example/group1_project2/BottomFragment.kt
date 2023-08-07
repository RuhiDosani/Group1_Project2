package com.example.group1_project2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.FrameMetrics.ANIMATION_DURATION
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BottomFragment: Fragment(R.layout.bottom_segment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wheelImageView: ImageView = view.findViewById(R.id.wheel_image)
        val bottomBackground: View = view.findViewById(R.id.bottom_background)
        val imageWeather: ImageView = view.findViewById(R.id.image_weather)

        val animImg2 = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        wheelImageView.startAnimation(animImg2)

        springTime(imageWeather, bottomBackground)
    }


 private fun springTime(image: ImageView, bg: View)
 {
     image.animate().apply {
         duration = 15000
         val colorAnim: ValueAnimator = ObjectAnimator.ofInt(
             bg, "backgroundColor",
             Color.parseColor("#FF4500"), //MediumVioletRed
             Color.parseColor("#8FBC8F"), //MediumVioletRed
         )
         colorAnim.duration = 15000
         colorAnim.repeatCount = ValueAnimator.INFINITE
         colorAnim.repeatMode = ValueAnimator.INFINITE
         colorAnim.setEvaluator(ArgbEvaluator())
         colorAnim.start()
     }.withStartAction {
         image.setImageResource(R.drawable.spring)
         image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

     }.withEndAction {
         image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out))

         summerTime(image,bg)
     }.start()


 }

    private fun summerTime(image: ImageView, bg: View)
    {
        image.animate().apply {
            duration = 15000
            val colorAnim: ValueAnimator = ObjectAnimator.ofInt(
                bg, "backgroundColor",
                Color.parseColor("#8FBC8F"), //MediumVioletRed
                Color.parseColor("#FFFF00"), //MediumVioletRed
            )
            colorAnim.duration = 15000
            colorAnim.repeatCount = ValueAnimator.INFINITE
            colorAnim.repeatMode = ValueAnimator.INFINITE
            colorAnim.setEvaluator(ArgbEvaluator())
            colorAnim.start()
        }.withStartAction {
            image.setImageResource(R.drawable.summer)
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        }.withEndAction {
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out))

            autumnTime(image,bg)
        }.start()
    }

    private fun autumnTime(image: ImageView, bg: View)
    {
        image.animate().apply {
            duration = 15000
            val colorAnim: ValueAnimator = ObjectAnimator.ofInt(
                bg, "backgroundColor",
                Color.parseColor("#FFFF00"), //MediumVioletRed
                Color.parseColor("#FFFFFF"), //MediumVioletRed
            )
            colorAnim.duration = 15000
            colorAnim.repeatCount = ValueAnimator.INFINITE
            colorAnim.repeatMode = ValueAnimator.INFINITE
            colorAnim.setEvaluator(ArgbEvaluator())
            colorAnim.start()
        }.withStartAction {
            image.setImageResource(R.drawable.autumn)
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        }.withEndAction {
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out))

            winterTime(image,bg)
        }.start()
    }

    private fun winterTime(image: ImageView, bg: View)
    {
        image.animate().apply {
            duration = 15000
            val colorAnim: ValueAnimator = ObjectAnimator.ofInt(
                bg, "backgroundColor",
                Color.parseColor("#FFFFFF"), //MediumVioletRed
                Color.parseColor("#FF4500"), //MediumVioletRed
            )
            colorAnim.duration = 15000
            colorAnim.repeatCount = ValueAnimator.INFINITE
            colorAnim.repeatMode = ValueAnimator.INFINITE
            colorAnim.setEvaluator(ArgbEvaluator())
            colorAnim.start()
        }.withStartAction {
            image.setImageResource(R.drawable.winter)
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        }.withEndAction {
            image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out))

            springTime(image,bg)
        }.start()
    }

}

//pushed