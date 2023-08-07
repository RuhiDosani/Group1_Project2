package com.example.group1_project2

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import android.media.MediaPlayer
import android.util.Log

class BottomFragment : Fragment(R.layout.bottom_segment) {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var currentAnimator: ValueAnimator
    private var currentSeasonIndex = 0

    private data class Season(
        val imageRes: Int,
        val songRes: Int,
        val startColor: Int,
        val endColor: Int
    )

    private val seasons = listOf(
        Season(
            R.drawable.spring,
            R.raw.spring_song,
            Color.parseColor("#FF4500"),
            Color.parseColor("#8FBC8F")
        ),
        Season(
            R.drawable.summer,
            R.raw.summer_song,
            Color.parseColor("#8FBC8F"),
            Color.parseColor("#FFFF00")
        ),
        Season(
            R.drawable.autumn,
            R.raw.autumn_song,
            Color.parseColor("#FFFF00"),
            Color.parseColor("#FFFFFF")
        ),
        Season(
            R.drawable.winter,
            R.raw.winter_song,
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#FF4500")
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wheelImageView: ImageView = view.findViewById(R.id.wheel_image)
        val bottomBackground: View = view.findViewById(R.id.bottom_background)
        val imageWeather: ImageView = view.findViewById(R.id.image_weather)

        val animImg2 = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        wheelImageView.startAnimation(animImg2)

        startSeasonAnimation(imageWeather, bottomBackground)
    }

    fun updateStatus(status: Boolean) {
        if (status) {
            currentAnimator.start()
        } else {
            currentAnimator.pause()
        }
    }

    @SuppressLint("RestrictedApi", "WrongConstant")
    private fun startSeasonAnimation(image: ImageView, bg: View) {
        val currentSeason = seasons[currentSeasonIndex]
        currentAnimator = ObjectAnimator.ofInt(
            bg, "backgroundColor", currentSeason.startColor, currentSeason.endColor
        ).apply {
            duration = 15000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            setEvaluator(ArgbEvaluator())
            addUpdateListener {
                bg.setBackgroundColor(it.animatedValue as Int)
            }
            start()
        }

        mediaPlayer = MediaPlayer.create(requireContext(), currentSeason.songRes)
        mediaPlayer.start()
        image.setImageResource(currentSeason.imageRes)
        image.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        currentAnimator.addPauseListener(object : Animator.AnimatorPauseListener {
            override fun onAnimationPause(animation: Animator) {
                mediaPlayer.pause()
            }

            override fun onAnimationResume(animation: Animator) {
                mediaPlayer.start()
            }
        })
    }
}