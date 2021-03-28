package com.flamecode.nomoretime.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.database.LocalStorage

/**
 * Builder for adding elements in list of preferences
 *
 */
class PreferenceListBuilder(private val context: Context) {

    fun create(first: Pair<String, Int>, second: Pair<String, Int>) : View{

        val mainView = View.inflate(context, R.layout.item_preference, null)

        val localStorage = LocalStorage(context)

        val firstImage = mainView.findViewById<ImageView>(R.id.firstImage)
        val firstText = mainView.findViewById<TextView>(R.id.firstText)

        val secondImage = mainView.findViewById<ImageView>(R.id.secondImage)
        val secondText = mainView.findViewById<TextView>(R.id.secondText)

        firstText.text = first.first
        firstImage.setImageResource(first.second)

        secondText.text = second.first
        secondImage.setImageResource(second.second)

        firstText.setOnClickListener {

            val alpha = if (it.alpha == 1f){
                0.5f
            } else {
                1f
            }

            it.animate().alpha(alpha)
            firstImage.animate().alpha(alpha)

            localStorage.setUserInterest(firstText.text.toString())
        }

        firstImage.setOnClickListener {

            val alpha = if (it.alpha == 1f){
                0.5f
            } else {
                1f
            }

            it.animate().alpha(alpha)
            firstText.animate().alpha(alpha)

            localStorage.setUserInterest(firstText.text.toString())
        }

        secondText.setOnClickListener {

            val alpha = if (it.alpha == 1f){
                0.5f
            } else {
                1f
            }

            it.animate().alpha(alpha)
            secondImage.animate().alpha(alpha)

            localStorage.setUserInterest(secondText.text.toString())
        }

        secondImage.setOnClickListener {

            val alpha = if (it.alpha == 1f){
                0.5f
            } else {
                1f
            }

            it.animate().alpha(alpha).startDelay = 100L
            secondText.animate().alpha(alpha).startDelay = 100L

            localStorage.setUserInterest(secondText.text.toString())
        }

        return mainView
    }
}