package com.example.jetpack_compose_all_in_one.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun bitmapDescriptorFromRes(
    context: Context,
    redId: Int,
    width: Dp? = null,
    height: Dp? = null
) = ContextCompat.getDrawable(context, redId)?.run {
        val w = width?.value?.toInt() ?: intrinsicWidth
        val h = height?.value?.toInt() ?: intrinsicHeight

        setBounds(0, 0, w, h)
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }