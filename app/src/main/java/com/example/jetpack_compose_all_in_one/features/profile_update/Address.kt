package com.example.jetpack_compose_all_in_one.features.profile_update

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String
)
