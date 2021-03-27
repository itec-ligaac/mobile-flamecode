package com.flamecode.nomoretime

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flamecode.nomoretime.fragment.SplashFragment
import com.flamecode.nomoretime.manager.FragmentManager
import com.here.android.mpa.common.ApplicationContext
import com.here.android.mpa.common.MapEngine
import com.here.android.mpa.common.MapSettings
import com.here.android.mpa.common.OnEngineInitListener
import java.io.File


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