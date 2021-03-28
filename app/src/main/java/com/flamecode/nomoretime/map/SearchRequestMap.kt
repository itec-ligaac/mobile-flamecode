package com.flamecode.nomoretime.map

import android.content.Context
import android.util.Log
import com.flamecode.nomoretime.R
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.mapping.Map
import com.here.android.mpa.search.DiscoveryRequest
import com.here.android.mpa.search.SearchRequest
import java.util.*

class SearchRequestMap {

    fun search(map : Map, context : Context, location : GeoCoordinate, typesOfSearch: TypesOfSearch? = null, search : String = "") {

        val request: DiscoveryRequest = if(typesOfSearch!= null) {

            SearchRequest(typesOfSearch.type).setSearchCenter(location)
        } else {

            val trim = search.decapitalize(Locale.getDefault()).trim()
            SearchRequest(trim).setSearchCenter(location)
        }

        request.collectionSize = 10
        request.execute { discoveryResultPage, errorCode ->

            val createMapObject = CreateMapObject()

            if (discoveryResultPage != null) {

                for (link in discoveryResultPage.placeLinks) {

                    val detailsRequest = link.category?.name
                    val trim = search.decapitalize(Locale.getDefault()).trim()
                    Log.d("SearchRequestMap", trim + detailsRequest + " " + link.title)
                    val element = com.flamecode.nomoretime.model.Place(
                        title = link.title, coord = link.position,
                        distance = link.distance, category = link.category?.name
                    )

                    val mapMarker = element.coord?.let {
                        createMapObject.create(
                            context, icon = R.drawable.placeholder,
                            location = it
                        )
                    }
                    mapMarker?.let { map.addMapObject(it) }
                }
            }
        }
    }
}