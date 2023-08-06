package com.example.group1_project2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.widget.Button
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator

class TopFragment : Fragment(R.layout.top_segment) {
    private var animView : View? = null
    private var ANIMATION_DURATION = 2000
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val birdImageView: ImageView = view.findViewById(R.id.imgBird)
        val cloudImageView: ImageView = view.findViewById(R.id.imgCloud)
        val sunImageView: ImageView = view.findViewById(R.id.imgSun)

        val animBird = AnimationUtils.loadAnimation(requireContext(), R.anim.move_n_repeat)
        val animSun = AnimationUtils.loadAnimation(requireContext(), R.anim.move_across)
        val animCloud = AnimationUtils.loadAnimation(requireContext(), R.anim.move_cloud)

        birdImageView.startAnimation(animBird)
        cloudImageView.startAnimation(animCloud)
        sunImageView.startAnimation(animSun)


        animView = view.findViewById(R.id.topBackground)

        val colorAnim: ValueAnimator = ObjectAnimator.ofInt(
            animView, "backgroundColor",
            Color.parseColor("#6B74FF"), //MediumVioletRed
            Color.parseColor("#6BEEFF"), //MediumVioletRed
        )
        colorAnim.duration = ANIMATION_DURATION.toLong()
        colorAnim.repeatCount = ValueAnimator.INFINITE
        colorAnim.repeatMode = ValueAnimator.REVERSE
        colorAnim.setEvaluator(ArgbEvaluator())
        colorAnim.start()
    }
}