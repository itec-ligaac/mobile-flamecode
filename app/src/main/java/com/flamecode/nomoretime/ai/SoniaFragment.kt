package com.flamecode.nomoretime.ai

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.fragment.MainFragment
import com.flamecode.nomoretime.manager.FragmentManager


class SoniaFragment : Fragment() {


    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sonia, container, false)
        getData(view)
        createSoniaAlgorithm(view)
        return view
    }

    private fun getData(view: View) {
        val goBack = view.findViewById<Button>(R.id.go_back)
        goBack.setOnClickListener {
            FragmentManager(fragmentManager!!).goBack(this)
        }
    }

    private fun createSoniaAlgorithm(view: View) {


        val askSoniaButton = view.findViewById<ImageView>(R.id.send_message)
        val answear = view.findViewById<TextView>(R.id.sonia_answear_tv)
        answear.visibility = View.INVISIBLE
        askSoniaButton.setOnClickListener {
            answear.visibility = View.VISIBLE
            answear.text = getAnswear(view)
        }

    }

    private fun getAnswear(view: View): String {
        val editText = view.findViewById<EditText>(R.id.ask_sonia_et)

        val questionStr = editText.text.toString().toLowerCase().trim()

        val restaurantX = 300
        val restaurantY = 400

        val museumX = 300
        val museumY = 400

        val theatreX = 300
        val theatreY = 400



        val map = mapOf(
            "restaurant" to "Most coordonates $restaurantX && $restaurantY"
            , "sights" to "Most beautifull sights you can find at coordonates $restaurantX && $restaurantY"
            , "museums" to "This museum is near to you at $museumX && $museumY"
            , "theatre" to "The theatre is near to you at $theatreX && $theatreY"
            , "pizza" to "You can order a pizza from $restaurantX && $restaurantY"
            , "burgers" to "You can eat a burger at coordonates $restaurantX && $restaurantY"
            , "dinner" to "You can take the dinner at $restaurantX && $restaurantY"
            ,"coffee" to "It would be a pleasure to drink a cofee at $restaurantX && $restaurantY"
            , "attraction" to "Some atractions are$restaurantX && $restaurantY"
        )

        val list = listOf("restaurant"
            , "sights"
            , "museums"
            , "theatre"
            , "pizza"
            , "burgers"
            , "romantic dinner"
            ,"coffee"
            , "attraction")


        for(i in list){
            Log.d("i", i)
            if(questionStr.contains(i)){
                return map[i].toString()
            }
        }


//        Log.d("asd", "${map[questionStr]}")
//        if(map[questionStr]!=null) return  map[questionStr].toString()
        return "Please reformulate the sentence"
    }

}