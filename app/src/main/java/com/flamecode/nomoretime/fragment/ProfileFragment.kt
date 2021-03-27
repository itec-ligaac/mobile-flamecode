package com.flamecode.nomoretime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.manager.FragmentManager

class ProfileFragment : Fragment() {
    private lateinit var visitedFrame : FrameLayout
    private lateinit var visitedImage : ImageView
    private lateinit var visitedText : TextView

    private lateinit var placesFrame : FrameLayout
    private lateinit var placesImage : ImageView
    private lateinit var placesText : TextView

    private lateinit var routesFrame : FrameLayout
    private lateinit var routesImage : ImageView
    private lateinit var routesText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)


        getData(view)
        return view
    }

    private fun getData(view: View) {
        visitedFrame = view.findViewById(R.id.frame_visited)
        visitedImage = view.findViewById(R.id.img_visited)
        visitedText = view.findViewById(R.id.text_visited)

        placesFrame = view.findViewById(R.id.frame_places)
        placesImage = view.findViewById(R.id.img_places)
        placesText = view.findViewById(R.id.text_places)

        routesFrame = view.findViewById(R.id.frame_routes)
        routesImage = view.findViewById(R.id.img_routes)
        routesText = view.findViewById(R.id.text_routes)

        onClickListeners()
    }

    private fun onClickListeners() {
        visitedText.setOnClickListener{ openVisitedFragment() }
        visitedImage.setOnClickListener { openVisitedFragment() }
        visitedFrame.setOnClickListener { openVisitedFragment() }

        placesText.setOnClickListener{ openPlacesFragment() }
        placesImage.setOnClickListener { openPlacesFragment() }
        placesFrame.setOnClickListener { openPlacesFragment() }

        routesText.setOnClickListener { openRoutesFragment() }
        routesImage.setOnClickListener { openRoutesFragment() }
        routesText.setOnClickListener { openRoutesFragment() }
    }

    private fun openRoutesFragment() { FragmentManager(fragmentManager!!).moveToNextFragment(RoutesFragment()) }

    private fun openPlacesFragment() { FragmentManager(fragmentManager!!).moveToNextFragment(VisitedHistoryFragment()) }

    private fun openVisitedFragment() { FragmentManager(fragmentManager!!).moveToNextFragment(BookmarkFragment()) }
}