package com.flamecode.nomoretime.fragment

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.flamecode.nomoretime.R

class SplashFragment : Fragment() {

    private lateinit var pulseAnimation : Animation


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash, container, false)

        getData(view)

        return view
    }

    private fun getData(view: View) {
        var appImage  = view.findViewById<ImageView>(R.id.app_icon)
        var appName = view.findViewById<TextView>(R.id.app_name)
        val pulseAnimation : Animation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        appImage.startAnimation(pulseAnimation)
        appImage.startAnimation(pulseAnimation)
    }
}