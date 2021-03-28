package com.flamecode.nomoretime.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flamecode.nomoretime.R
import com.flamecode.nomoretime.manager.FragmentManager
import com.flamecode.nomoretime.map.TypesOfSearch
import com.flamecode.nomoretime.util.HaversineInKM
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.search.DiscoveryRequest
import com.here.android.mpa.search.SearchRequest
import java.util.*

class RoutesFragment : Fragment() {

    private var startDistance : GeoCoordinate = GeoCoordinate(0.0, 0.0)
    private var endDistance : GeoCoordinate = GeoCoordinate(0.0, 0.0)

    lateinit var responseCalcTextView : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_routes, container, false)

        val startLocationEditText = view.findViewById<EditText>(R.id.start_location)
        val endLocationEditText = view.findViewById<EditText>(R.id.end_location)
        val calculateButton = view.findViewById<Button>(R.id.calculate)

        responseCalcTextView = view.findViewById(R.id.responseCalc)

        calculateButton.setOnClickListener {

            var startNotEmpty = false
            var endNotEmpty = false

            if (startLocationEditText.text.isEmpty() || startLocationEditText.text.isNullOrBlank()) {

                Toast.makeText(context, "Start location can not be empty", Toast.LENGTH_LONG).show()
            } else {

                startNotEmpty = true
            }

            if (endLocationEditText.text.isEmpty() || startLocationEditText.text.isNullOrBlank()) {

                Toast.makeText(context, "End location can not be empty", Toast.LENGTH_LONG).show()
            } else {

                endNotEmpty = true
            }

            if (startNotEmpty && endNotEmpty) {

                val startText = startLocationEditText.text.toString()
                    .decapitalize(Locale.getDefault()).trim()
                val endText = endLocationEditText.text.toString()
                    .decapitalize(Locale.getDefault()).trim()

                uniqueSearch(search = startText)
                uniqueSearch(search = endText)
            }
        }

        getData(view)
        return view
    }

    private fun getData(view: View) {
        val goBack = view.findViewById<Button>(R.id.go_back)
        goBack.setOnClickListener {
            FragmentManager(fragmentManager!!).goBack(this)
        }
    }

    private fun uniqueSearch(
        typesOfSearch: TypesOfSearch? = null,
        search: String = "") {

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

                    if (startDistance.latitude != 0.0 && startDistance.longitude != 0.0){

                        endDistance = element.coord!!
                        val distance = HaversineInKM(
                            startDistance.latitude,
                            startDistance.longitude, endDistance.latitude, endDistance.longitude
                        )

                        responseCalcTextView.text = String.format("%.2f", distance).toDouble().toString() + " km"

                        startDistance = GeoCoordinate(0.0, 0.0)
                        endDistance = GeoCoordinate(0.0, 0.0)
                    } else {

                        startDistance = element.coord!!
                    }
                }
            }
        }
    }

}