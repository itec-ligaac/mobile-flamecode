package com.flamecode.nomoretime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flamecode.nomoretime.fragment.SplashFragment
import com.flamecode.nomoretime.manager.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openSplashFragment()
    }

    private fun openSplashFragment() {
        FragmentManager(supportFragmentManager).moveToNextFragment(SplashFragment())
    }
}