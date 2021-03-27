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

    private fun initMapEngine() {
        val diskCacheRoot: String = Environment.getExternalStorageDirectory()
            .getPath() + File.separator.toString() + ".isolated-here-maps"
        var intentName = ""
        try {
            val ai: ApplicationInfo = this.packageManager
                .getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
            val bundle = ai.metaData
            intentName = bundle.getString("INTENT_NAME")!!
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(
                this.javaClass.toString(),
                "Failed to find intent name, NameNotFound: " + e.message
            )
        }
        val success = MapSettings.setIsolatedDiskCacheRootPath(diskCacheRoot)
        if (!success) {
        } else {
            MapEngine.getInstance().init(
                ApplicationContext(this)
            ) { error -> //Here is
                Log.e(
                    "TAG",
                    "Map Engine initialized with error code: $error"
                )
                Toast.makeText(
                    this,
                    "Map Engine initialized with error code:$error",
                    Toast.LENGTH_SHORT
                ).show()
                if (error == OnEngineInitListener.Error.NONE) {
                   // getCoordinatesForAddress(mAddress)
                }
            }
        }
    }
}