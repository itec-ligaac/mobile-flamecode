package com.flamecode.nomoretime

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flamecode.nomoretime.fragment.SplashFragment
import com.flamecode.nomoretime.manager.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openSplashFragment()
        setColorForNavigationBar()
    }

    private fun setColorForNavigationBar() {

        // set the bottom bar a new color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.navigationBarColor = resources.getColor(R.color.black, theme)
        }
    }

    private fun openSplashFragment() {

        FragmentManager(supportFragmentManager).moveToNextFragment(SplashFragment())
    }
}