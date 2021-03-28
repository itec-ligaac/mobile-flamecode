package com.flamecode.nomoretime.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.manager.FragmentManager

// TODO: Rename parameter arguments, choose names that match

class VisitedHistoryFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_visited_history, container, false)

        getData(view)
        return view
    }

    private fun getData(view: View) {
        val goBack = view.findViewById<Button>(R.id.go_back)
        goBack.setOnClickListener { FragmentManager(fragmentManager!!).goBack(MainFragment()) }
    }


}