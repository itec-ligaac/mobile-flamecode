package com.flamecode.nomoretime.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.map.SearchRequestMap
import com.here.android.mpa.common.*
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map
import com.here.android.mpa.mapping.MapGesture
import com.here.android.mpa.mapping.MapMarker
import com.location.aravind.getlocation.GeoLocator

class HomeFragment(private val viewPager2: ViewPager2) : Fragment() {

    companion object {

        const val TAG_CODE_PERMISSION_LOCATION = 11113
        const val ZOOM_LEVEL = 14.0
        const val DURATION = 300L
    }

    private var map: Map? = null
    lateinit var mapFragment : AndroidXMapFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        searchFooter(view)

        initMapService()

        return view
    }

    private fun searchFooter(view: View) {

        val searchLayout = view.findViewById<ConstraintLayout>(R.id.searchLayout)
        val searchImageView = view.findViewById<ImageView>(R.id.searchImageView)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        val searchLayoutSecond = view.findViewById<ConstraintLayout>(R.id.searchLayoutSecond)
        val searchImageViewSecond = view.findViewById<ImageView>(R.id.searchImageViewSecond)

        animClickListenersFooter(
            searchLayout,
            searchEditText,
            searchLayoutSecond,
            searchImageViewSecond,
            searchImageView
        )

        searchLayoutSecond.setOnClickListener(searchPlacesOnClickListener(searchEditText))
        searchImageViewSecond.setOnClickListener(searchPlacesOnClickListener(searchEditText))
    }

    private fun searchPlacesOnClickListener(editText: EditText) : View.OnClickListener{

        val geoLocator = GeoLocator(context, activity)

        return View.OnClickListener {

            val actualLocation = GeoCoordinate(
                geoLocator.lattitude,
                geoLocator.longitude
            )

            map?.removeAllMapObjects()
            setHome(actualLocation)

            map?.let { it1 ->
                context?.let { it2 ->
                    SearchRequestMap().search(
                        it1, it2, GeoCoordinate(geoLocator.lattitude, geoLocator.longitude),
                        search = editText.text.toString())
                }
            }
        }
    }

    private fun animClickListenersFooter(
        searchLayout: ConstraintLayout,
        searchEditText: EditText,
        searchLayoutSecond: ConstraintLayout,
        searchImageViewSecond: ImageView,
        searchImageView: ImageView
    ) {

        searchLayout.setOnClickListener {

            firstAnimFooter(searchLayout, searchEditText, searchLayoutSecond, searchImageViewSecond)
        }

        searchImageView.setOnClickListener {

            firstAnimFooter(searchLayout, searchEditText, searchLayoutSecond, searchImageViewSecond)
        }

        searchLayoutSecond.setOnLongClickListener {

            secondAnimFooter(
                searchLayout,
                searchEditText,
                searchLayoutSecond,
                searchImageViewSecond
            )
        }

        searchImageViewSecond.setOnLongClickListener {

            secondAnimFooter(
                searchLayout,
                searchEditText,
                searchLayoutSecond,
                searchImageViewSecond
            )
        }
    }

    private fun secondAnimFooter(
        searchLayout: ConstraintLayout,
        searchEditText: EditText,
        searchLayoutSecond: ConstraintLayout,
        searchImageViewSecond: ImageView
    ): Boolean {

        searchLayout.animate().alpha(1f).setDuration(DURATION)
            .withEndAction { searchLayout.visibility = View.VISIBLE }
        searchEditText.animate().alpha(0f).setDuration(DURATION)
            .withStartAction { searchEditText.visibility = View.GONE }
        searchLayoutSecond.animate().alpha(0f).setDuration(DURATION)
            .withStartAction { searchLayoutSecond.visibility = View.GONE }
        searchImageViewSecond.animate().alpha(0f).setDuration(DURATION)
            .withStartAction { searchLayoutSecond.visibility = View.GONE }

        return true
    }

    private fun firstAnimFooter(
        searchLayout: ConstraintLayout,
        searchEditText: EditText,
        searchLayoutSecond: ConstraintLayout,
        searchImageViewSecond: ImageView
    ) {

        searchLayout.animate().alpha(0f).setDuration(DURATION)
            .withEndAction { searchLayout.visibility = View.GONE }
        searchEditText.animate().alpha(1f).setDuration(DURATION)
            .withStartAction { searchEditText.visibility = View.VISIBLE }
        searchLayoutSecond.animate().alpha(1f).setDuration(DURATION)
            .withStartAction { searchLayoutSecond.visibility = View.VISIBLE }
        searchImageViewSecond.animate().alpha(1f).setDuration(DURATION)
            .withStartAction { searchLayoutSecond.visibility = View.VISIBLE }
    }

    private fun initMapService() {

        mapFragment = AndroidXMapFragment()
        val fm: FragmentManager = childFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.simpleFrameLayout, mapFragment).commit()

        mapFragment.init(ApplicationContext(context!!)) { error ->

            if (error == OnEngineInitListener.Error.NONE) {

                // retrieve a reference of the map from the map fragment
                map = mapFragment.map
                configureMap()
                addGestureListenerForMap()
                //   SearchRequestMap().search()

                val geoLocator = GeoLocator(context, activity)
                val actualLocation = GeoCoordinate(
                    geoLocator.lattitude,
                    geoLocator.longitude
                )

                setHome(actualLocation)

            } else {
                Log.e("TAG", "ERROR: Cannot initialize Map Fragment: " + error.stackTrace)
            }
        }
    }

    private fun setHome(actualLocation: GeoCoordinate) {

        val img = Image()
        val decodeResource = BitmapFactory.decodeResource(resources, R.drawable.home_location)
        img.setBitmap(
            Bitmap.createScaledBitmap(
                decodeResource, 100, 100,
                false
            )
        )

        map?.addMapObject(MapMarker(actualLocation, img))
    }

    private fun configureMap() {

        map?.setZoomLevel(ZOOM_LEVEL, Map.Animation.LINEAR)
        setUserLocation()
        map?.setLandmarksVisible(true)
        map?.mapScheme = map?.mapSchemes!![1] // NIGHT MODE FOR MAP
    }

    override fun onResume() {

        super.onResume()
        mapFragment.onResume()
    }

    override fun onPause() {

        super.onPause()
        mapFragment.onPause()
    }

    private fun setUserLocation() {

        if (ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED) {

            val geoLocator = GeoLocator(context, activity)
            val actualLocation = GeoCoordinate(
                geoLocator.lattitude,
                geoLocator.longitude
            )

            map?.setCenter(
                actualLocation, Map.Animation.LINEAR)

        } else {
            ActivityCompat.requestPermissions(
                activity!!, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                TAG_CODE_PERMISSION_LOCATION
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == TAG_CODE_PERMISSION_LOCATION) {

            val geoLocator = GeoLocator(context, activity)
            map?.setCenter(
                GeoCoordinate(geoLocator.lattitude,
                    geoLocator.longitude), Map.Animation.LINEAR)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.d("TAG", "onRequestPermissionsResult")
    }

    private fun addGestureListenerForMap() {

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
                viewPager2.isUserInputEnabled = false
            }

            override fun onMultiFingerManipulationEnd() {
                Log.d("mapGesture", "onMultiFingerManipulationEnd")
                viewPager2.isUserInputEnabled = true
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
    }
}