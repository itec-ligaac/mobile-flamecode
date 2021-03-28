package com.flamecode.nomoretime.ai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.manager.FragmentManager
import com.flamecode.nomoretime.map.TypesOfSearch
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.search.DiscoveryRequest
import com.here.android.mpa.search.SearchRequest
import java.util.*

class SoniaFragment : Fragment() {

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

        val answer = view.findViewById<TextView>(R.id.sonia_answear_tv)

        val askSoniaButton = view.findViewById<ImageView>(R.id.send_message)
        answer.visibility = View.INVISIBLE
        askSoniaButton.setOnClickListener {

            answer.visibility = View.VISIBLE
           getAnswer(view)
        }

    }

    private fun getAnswer(view: View): String {

        val answer = view.findViewById<TextView>(R.id.sonia_answear_tv)

        val editText = view.findViewById<EditText>(R.id.ask_sonia_et)
        val questionStr = editText.text.toString().toLowerCase(Locale.ROOT).trim()

        val map = mapOf(
            "restaurant" to "The closest restaurant is"
            , "sights" to "Most beautiful sights you can find at coordinates"
            , "museums" to "This museum is near to you at"
            , "theatre" to "The theatre is near to you at"
            , "pizza" to "You can order a pizza from"
            , "burgers" to "You can eat a burger at coordinates"
            , "dinner" to "You can take the dinner at"
            ,"coffee" to "It would be a pleasure to drink a cofee at"
            , "attraction" to "Some attractions are"
        )

        val list = listOf("restaurant"
            , "sights"
            , "museums"
            , "theatre"
            , "pizza"
            , "burgers"
            , "dinner"
            ,"coffee"
            , "attraction")


        for(item in list) {

            if(questionStr.contains(item)) {

                uniqueSearch(search = item, answear = answer, additionalString = map[item].toString())

                return item
            }
        }

        return "Please reformulate the sentence"
    }

    private fun uniqueSearch(
        typesOfSearch: TypesOfSearch? = null,
        search: String = "", answear : TextView, additionalString : String) {

        val request: DiscoveryRequest = if(typesOfSearch!= null) {

            SearchRequest(typesOfSearch.type).setSearchCenter(GeoCoordinate(0.0, 0.0))
        } else {

            val trim = search.decapitalize(Locale.getDefault()).trim()
            SearchRequest(trim).setSearchCenter(GeoCoordinate(47.15, 27.5))
        }

        request.collectionSize = 1
        request.execute { discoveryResultPage, errorCode ->

            if (discoveryResultPage != null) {

                for (link in discoveryResultPage.placeLinks) {

                    val element = com.flamecode.nomoretime.model.Place(
                        title = link.title, coord = link.position,
                        distance = link.distance, category = link.category?.name
                    )

                    answear.text = additionalString + " at ${element.title} (lat = ${element.coord?.latitude} and  long = ${element.coord?.longitude})"
                }
            }
        }
    }

}