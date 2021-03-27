package com.flamecode.nomoretime.map

import android.util.Log
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.search.AddressFilter
import com.here.android.mpa.search.DiscoveryRequest
import com.here.android.mpa.search.ErrorCode
import com.here.android.mpa.search.SearchRequest

class SearchRequestMap {

    fun search(location : GeoCoordinate, typesOfSearch: TypesOfSearch) {

        val request: DiscoveryRequest = SearchRequest(typesOfSearch.type).setSearchCenter(location)
        request.collectionSize = 20
        request.execute { discoveryResultPage, errorCode ->

            if (discoveryResultPage != null) {

                for (link in discoveryResultPage.placeLinks) {

                    val detailsRequest = link.category?.name
                    Log.d("SearchRequestMap", "" + detailsRequest)
                }
            }
        }
    }
}