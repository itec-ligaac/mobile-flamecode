package com.flamecode.nomoretime.ai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.fragment.MainFragment
import com.flamecode.nomoretime.manager.FragmentManager


class SoniaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sonia, container, false)

        getData(view)

        return view
    }

    private fun getData(view: View) {
        val goBack = view.findViewById<Button>(R.id.go_back)
        goBack.setOnClickListener { FragmentManager(fragmentManager!!).moveToNextFragment(
            MainFragment()
        ) }
    }

}