package com.flamecode.nomoretime.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.Image
import com.here.android.mpa.mapping.MapMarker

class CreateMapObject {

    fun create(context: Context, icon: Int, location: GeoCoordinate) : MapMarker{

        val img = Image()
        val decodeResource = BitmapFactory.decodeResource(context.resources, icon)
        img.setBitmap(
            Bitmap.createScaledBitmap(
                decodeResource, 100, 100,
                false
            )
        )

        return MapMarker(location, img)
    }
}