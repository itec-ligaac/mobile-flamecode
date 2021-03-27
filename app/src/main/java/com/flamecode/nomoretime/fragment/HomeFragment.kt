package com.flamecode.nomoretime.fragment

import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.flamecode.nomoretime.R
import com.here.android.mpa.common.ApplicationContext
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.common.ViewObject
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map
import com.here.android.mpa.mapping.MapGesture


class HomeFragment(private val viewPager2 : ViewPager2) : Fragment() {

    companion object {

        var onTouch = false
    }

    private var map: Map? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val mapFragment = AndroidXMapFragment()
        val fm: FragmentManager = fragmentManager!!
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.simpleFrameLayout, mapFragment).commit()

        mapFragment.init(ApplicationContext(context!!)) { error ->
            if (error == OnEngineInitListener.Error.NONE) {

                // retrieve a reference of the map from the map fragment
                map = mapFragment.map
                mapFragment.mapGesture?.addOnGestureListener(object : MapGesture.OnGestureListener {
                    override fun onPanStart() {
                    Log.d("mapGesture", "onPanStart")

                        viewPager2.isUserInputEnabled = false
                    }

                    override fun onPanEnd() {
                        Log.d("mapGesture", "onPanEnd")
                        viewPager2.isUserInputEnabled = true
                    }

                    override fun onMultiFingerManipulationStart() {
                        Log.d("mapGesture", "onMultiFingerManipulationStart")
                    }

                    override fun onMultiFingerManipulationEnd() {
                        Log.d("mapGesture", "onMultiFingerManipulationEnd")
                    }

                    override fun onMapObjectsSelected(p0: MutableList<ViewObject>): Boolean {
                        Log.d("mapGesture", "onMapObjectsSelected")
                        return true
                    }

                    override fun onTapEvent(p0: PointF): Boolean {
                        Log.d("mapGesture", "onTapEvent")
                        return false
                    }

                    override fun onDoubleTapEvent(p0: PointF): Boolean {
                        Log.d("mapGesture", "onDoubleTapEvent")
                        return true
                    }

                    override fun onPinchLocked() {
                        Log.d("mapGesture", "onPinchLocked")
                    }

                    override fun onPinchZoomEvent(p0: Float, p1: PointF): Boolean {
                        Log.d("mapGesture", "onPinchZoomEvent")
                        return false
                    }

                    override fun onRotateLocked() {
                        Log.d("mapGesture", "onRotateLocked")
                    }

                    override fun onRotateEvent(p0: Float): Boolean {
                        Log.d("mapGesture", "onRotateEvent")
                        return true
                    }

                    override fun onTiltEvent(p0: Float): Boolean {
                        Log.d("mapGesture", "onTiltEvent")

                        return false
                    }

                    override fun onLongPressEvent(p0: PointF): Boolean {
                        Log.d("mapGesture", "onLongPressEvent")
                        return false
                    }

                    override fun onLongPressRelease() {
                        Log.d("mapGesture", "onLongPressRelease")
                    }

                    override fun onTwoFingerTapEvent(p0: PointF): Boolean {
                        Log.d("mapGesture", "onTwoFingerTapEvent")
                        return true
                    }

                }, 1, true)

            } else {
                Log.e("TAG", "ERROR: Cannot initialize Map Fragment: " + error.stackTrace)
            }
        }

        return view
    }

}