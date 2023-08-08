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
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.group1_project2.databinding.BottomSegmentBinding

class BottomFragment : Fragment(R.layout.bottom_segment) {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var currentAnimator: ValueAnimator
    private var _binding: BottomSegmentBinding? = null
    private val binding get() = _binding!!

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


        val animImg2 = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        binding.wheelImage.startAnimation(animImg2)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSegmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    fun updateStatus(status: Boolean) {
        if (status) {
            stopMediaPlayer()
            currentAnimator.cancel()
            startSeasonAnimation(0)
        } else {
            stopMediaPlayer()
            currentAnimator.pause()
            binding.wheelImage.clearAnimation()
        }
    }

    fun updateAnimate(animation: Int) {
        stopMediaPlayer()
        startSeasonAnimation(animation)
    }

    fun stopMediaPlayer() {
        if(mediaPlayer?.isPlaying == true){
            mediaPlayer?.stop()
        }
    }

    @SuppressLint("RestrictedApi", "WrongConstant")
    private fun startSeasonAnimation(index: Int) {
        val currentSeason = seasons[index]
        Log.d("TAG-index", index.toString())

        mediaPlayer = MediaPlayer.create(requireContext(), currentSeason.songRes)

        if(mediaPlayer?.isPlaying == true){
            mediaPlayer?.stop()
        } else {
            mediaPlayer?.start()
        }


        currentAnimator = ObjectAnimator.ofInt(
            binding.bottomBackground, "backgroundColor", currentSeason.startColor, currentSeason.endColor
        ).apply {
            duration = 15000
            repeatCount = ValueAnimator.INFINITE
            setEvaluator(ArgbEvaluator())
            addUpdateListener {
                binding.bottomBackground.setBackgroundColor(it.animatedValue as Int)
            }
                start()
        }
        binding.imageWeather.setImageResource(currentSeason.imageRes)
        binding.imageWeather.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in))

        currentAnimator.addPauseListener(object : Animator.AnimatorPauseListener {
            override fun onAnimationPause(animation: Animator) {
               // mediaPlayer?.pause()
            }

            override fun onAnimationResume(animation: Animator) {
               // mediaPlayer?.start()
            }
        })
    }
}