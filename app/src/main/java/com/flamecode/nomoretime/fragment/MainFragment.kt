package com.flamecode.nomoretime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.ui.DotIndicatorAdapter
import me.relex.circleindicator.CircleIndicator3


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        addViewPagerAndCircleIndicator(view)

        return view
    }

    private fun addViewPagerAndCircleIndicator(view: View) {

        val viewpager: ViewPager2 = view.findViewById(R.id.viewpager2)
        viewpager.offscreenPageLimit = 3
        viewpager.adapter = activity?.let { DotIndicatorAdapter(it) }
        viewpager.currentItem = 1

        val circleIndicator3 = view.findViewById<CircleIndicator3>(R.id.indicator)
        circleIndicator3.setViewPager(viewpager)
    }
}