package com.flamecode.nomoretime.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.flamecode.nomoretime.R
import com.here.android.mpa.common.ApplicationContext
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map


class HomeFragment : Fragment() {

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

        mapFragment.init(ApplicationContext(context!!), OnEngineInitListener { error ->
            if (error == OnEngineInitListener.Error.NONE) {

                // retrieve a reference of the map from the map fragment
                map = mapFragment.map
            } else {
                Log.e("TAG","ERROR: Cannot initialize Map Fragment: " + error.stackTrace)
            }
        })

        return view
    }

}