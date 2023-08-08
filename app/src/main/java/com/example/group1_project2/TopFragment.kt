package com.example.group1_project2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TopFragment : Fragment(R.layout.top_segment) {
    private var animView : View? = null
    private var ANIMATION_DURATION = 2000
    var mCallback: ButtoClickedListener? = null
    var seasonIndex: Int = 0
    val scope = MainScope() // could also use an other scope such as viewModelScope if available
    var job: Job? = null

    interface ButtoClickedListener {
        fun setStatus(status: Boolean)
        fun setAnimate(animation: Int)
    }

    fun setOnButtoClickedListener(callback: ButtoClickedListener) {
        this.mCallback = callback
    }

    fun startUpdates() {
        job = scope.launch {
            while(true) {
                mCallback?.setAnimate(seasonIndex)
                seasonIndex += 1
                Log.d("Top-fragment", seasonIndex.toString())
                if(seasonIndex==4) {
                    seasonIndex = 0
                }
                delay(15000)
            }
        }
    }

    private fun stopUpdates() {
        job?.cancel()
        job = null
    }


    @SuppressLint("ObjectAnimatorBinding", "RestrictedApi")
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

        val startButton:Button = view.findViewById(R.id.start_button)
        val stopButton:Button = view.findViewById(R.id.stop_button)

        startUpdates()

        startButton.setOnClickListener(){
            seasonIndex = 0
            startUpdates()
            mCallback?.setStatus(true)
        }

        stopButton.setOnClickListener() {
            stopUpdates()
            mCallback?.setStatus(false)
        }
    }
}

