package com.flamecode.nomoretime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.database.LocalStorage
import com.flamecode.nomoretime.manager.FragmentManager
import com.flamecode.nomoretime.ui.PreferenceListBuilder

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

        val list = listOf(Pair("Restaurant", R.drawable.ic_restaurant),
                Pair("Coffee-Tea", R.drawable.ic_coffe), Pair("Entertainment", R.drawable.ic_cocktail),
                Pair("Sights and Museums", R.drawable.ic_pantheon), Pair("Cinema", R.drawable.ic_popcorn),
                Pair("Theatre and Music", R.drawable.ic_culture),
                Pair("Natural and Geographical", R.drawable.ic_trees), Pair("Transport", R.drawable.ic_bus),
                Pair("Accommodations", R.drawable.ic_hotel), Pair("Shopping", R.drawable.ic_online_shopping))

        val listOfPreferences = view.findViewById<LinearLayout>(R.id.listOfPreferences)

        addOnList(listOfPreferences, list)

        initAnim(view, finnishButton)

        return view
    }

    private fun addOnList(listOfPreferences: LinearLayout, list: List<Pair<String, Int>>) {

        val preferenceListBuilder = context?.let { PreferenceListBuilder(it) }

        for (it in list.indices step 2) {

            listOfPreferences.addView(preferenceListBuilder?.create(list[it], list[it + 1]))
        }
        createSpaceAtTheBottomOfList(listOfPreferences)
    }

    private fun createSpaceAtTheBottomOfList(listOfPreferences: LinearLayout) {

        for (i in 1..5) {

            listOfPreferences.addView(View.inflate(context, R.layout.spacer, null))
        }
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