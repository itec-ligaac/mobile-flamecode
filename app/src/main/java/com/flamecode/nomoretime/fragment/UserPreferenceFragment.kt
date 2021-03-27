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

class UserPreferenceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_preference, container, false)

        val finnishButton = view.findViewById<Button>(R.id.finnishButton)
        finnishButton.setOnClickListener {

            createUser()
        }

        initAnim(view, finnishButton)

        return view
    }

    private fun createUser() {

        val localStorage = context?.let { LocalStorage(it) }
        localStorage?.createUser()
    }

    private fun initAnim(view: View, finnishButton: Button) {

        val t1 = view.findViewById<TextureView>(R.id.t1)
        val t2 = view.findViewById<TextureView>(R.id.t2)
        val t3 = view.findViewById<TextureView>(R.id.t3)
        val content = view.findViewById<TextView>(R.id.content)

        t1.animate().alpha(1f)
        t2.animate().alpha(1f).startDelay = 100L
        t3.animate().alpha(1f).startDelay = 150L
        content.animate().alpha(1f).startDelay = 200L
        finnishButton.animate().alpha(1f).startDelay = 250L
    }
}