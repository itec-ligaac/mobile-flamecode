package com.flamecode.nomoretime.model

import com.here.android.mpa.common.GeoCoordinate

/**
 * Model for storing a place
 *
 */
class Place(val title : String, val coord : GeoCoordinate?, val distance : Double?,
            val category : String?)
