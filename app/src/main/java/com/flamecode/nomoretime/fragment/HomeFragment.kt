package com.flamecode.nomoretime.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flamecode.nomoretime.R
import com.here.android.mpa.common.ApplicationContext
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.MapEngine
import com.here.android.mpa.common.OnEngineInitListener
import com.here.android.mpa.mapping.AndroidXMapFragment
import com.here.android.mpa.mapping.Map


class HomeFragment : Fragment() {

    private var map : Map = Map()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)



        return view
    }

}