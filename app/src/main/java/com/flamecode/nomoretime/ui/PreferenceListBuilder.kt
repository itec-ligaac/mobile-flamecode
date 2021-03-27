package com.flamecode.nomoretime.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.flamecode.nomoretime.R

class PreferenceListBuilder(private val context: Context) {

    fun create(first: Pair<String, Int>, second: Pair<String, Int>) : View{

        val mainView = View.inflate(context, R.layout.item_preference, null)

        val firstImage = mainView.findViewById<ImageView>(R.id.firstImage)
        val firstText = mainView.findViewById<TextView>(R.id.firstText)

        val secondImage = mainView.findViewById<ImageView>(R.id.secondImage)
        val secondText = mainView.findViewById<TextView>(R.id.secondText)

        firstText.text = first.first
        firstImage.setImageResource(first.second)

        secondText.text = second.first
        secondImage.setImageResource(second.second)

        return mainView
    }
}