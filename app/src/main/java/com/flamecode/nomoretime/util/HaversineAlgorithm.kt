package com.flamecode.nomoretime.util

import kotlin.math.*

object HaversineAlgorithm {

    const val _eQuatorialEarthRadius = 6378.1370
    const val _d2r = Math.PI / 180.0

    fun HaversineInM(lat1: Double, long1: Double, lat2: Double, long2: Double): Int {

        return (1000.0 * HaversineInKM(lat1, long1, lat2, long2)).toInt()
    }

}

fun HaversineInKM(lat1: Double, long1: Double, lat2: Double, long2: Double): Double {

    val dlong = (long2 - long1) * HaversineAlgorithm._d2r
    val dlat = (lat2 - lat1) * HaversineAlgorithm._d2r
    val a = sin(dlat / 2.0).pow(2.0) + (cos(lat1 * HaversineAlgorithm._d2r) * cos(lat2 * HaversineAlgorithm._d2r)
            * sin(dlong / 2.0).pow(2.0))
    val c = 2.0 * atan2(sqrt(a), sqrt(1.0 - a))
    return HaversineAlgorithm._eQuatorialEarthRadius * c
}