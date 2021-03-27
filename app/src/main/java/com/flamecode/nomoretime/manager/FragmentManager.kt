package com.flamecode.nomoretime.manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flamecode.nomoretime.R

/**
 * Manager for using fast and easy fragments and navigate in app
 */
class FragmentManager(private val fragmentManager: FragmentManager) {

    /**
     * Replace the fragment
     */
    fun moveToNextFragment(nextFrag : Fragment, container : Int = R.id.containerMain) {

        fragmentManager.beginTransaction().replace(container, nextFrag).commit()
    }

    /**
     * Add a new fragment on top
     */
    fun addFragment(nextFrag : Fragment) {

        fragmentManager.beginTransaction().add(R.id.containerMain, nextFrag).commit()
    }

    /**
     * Go back ->  means to remove the actual fragment
     */
    fun goBack(fragment: Fragment) {

        fragmentManager.beginTransaction().remove(fragment).commit()
    }
}