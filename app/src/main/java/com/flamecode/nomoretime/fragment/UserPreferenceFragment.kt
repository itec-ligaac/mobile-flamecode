package com.flamecode.nomoretime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.database.LocalStorage
import com.flamecode.nomoretime.manager.FragmentManager

class UserPreferenceFragment : Fragment() {

    companion object {

        const val ANIM_DELAY = 100L
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_preference, container, false)

        val finnishButton = view.findViewById<Button>(R.id.finnishButton)
        finnishButton.setOnClickListener {

            // createUser()
            fragmentManager?.let { it1 -> FragmentManager(it1).moveToNextFragment(MainFragment()) }
        }

        initAnim(view, finnishButton)

        return view
    }

    private fun createUser() {

        val localStorage = context?.let { LocalStorage(it) }
        localStorage?.createUser()
    }

    private fun initAnim(view: View, finnishButton: Button) {

        val t1 = view.findViewById<TextView>(R.id.t1)
        val t2 = view.findViewById<TextView>(R.id.t2)
        val t3 = view.findViewById<TextView>(R.id.t3)
        val content = view.findViewById<TextView>(R.id.content)

        t1.animate().alpha(1f)
        t2.animate().alpha(1f).startDelay = ANIM_DELAY
        t3.animate().alpha(1f).startDelay = ANIM_DELAY * 2
        content.animate().alpha(1f).startDelay = ANIM_DELAY * 3
        finnishButton.animate().alpha(1f).startDelay = ANIM_DELAY * 4
    }
}