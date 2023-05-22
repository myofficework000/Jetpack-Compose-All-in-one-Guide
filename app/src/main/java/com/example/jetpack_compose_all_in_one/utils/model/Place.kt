package com.example.jetpack_compose_all_in_one.utils.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import kotlin.random.Random

@Immutable
data class Place(
    val id: Long,
    val description: String,
    @DrawableRes val imgRes: Int,
    val rating: Double = Random.nextDouble(0.0, 10.0),
    val price: Int = Random.nextInt(500)
)