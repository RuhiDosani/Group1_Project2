package com.example.group1_project2

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BottomFragment: Fragment(R.layout.bottom_segment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wheelImageView: ImageView = view.findViewById(R.id.wheel_image)

        val animImg2 = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        wheelImageView.startAnimation(animImg2)

    }


}