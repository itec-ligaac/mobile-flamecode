package com.flamecode.nomoretime.fragment

import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.database.LocalStorage

class SplashFragment : Fragment() {



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
        val appImage  = view.findViewById<ImageView>(R.id.app_icon)
        val appName = view.findViewById<TextView>(R.id.app_name)
        val pulseAnimation : Animation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        pulseAnimation.repeatCount = Animation.INFINITE
        appImage.startAnimation(pulseAnimation)


        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 1500
        appName.animation = fadeIn


        pulseAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                if(allDataCollected()){
                    com.flamecode.nomoretime.manager.FragmentManager(fragmentManager!!).moveToNextFragment(HomeFragment())
                } else {
                    com.flamecode.nomoretime.manager.FragmentManager(fragmentManager!!).moveToNextFragment(MainFragment())
                }
            }
            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }

    fun allDataCollected() : Boolean{
        return LocalStorage(context!!).getUser() != null
    }


}