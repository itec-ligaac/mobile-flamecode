package com.flamecode.nomoretime.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.flamecode.nomoretime.fragment.HomeFragment
import com.flamecode.nomoretime.fragment.NewsFragment
import com.flamecode.nomoretime.fragment.ProfileFragment

/**
 * Adapter for switching the home fragments
 * @see
 */
class DotIndicatorAdapter(private val viewPager2: ViewPager2, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val MAX_SIZE = 3

    override fun getItemCount(): Int = MAX_SIZE

    override fun createFragment(position: Int): Fragment = when(position) {

        0 -> {NewsFragment()}
        1 -> {HomeFragment(viewPager2)}
        2 -> { ProfileFragment() }
        else -> {HomeFragment(viewPager2)}
    }
}