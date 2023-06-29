package com.example.jetpack_compose_all_in_one.features.profile_update

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: Long = 0,

    @ColumnInfo(name = "userName")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "about")
    var about: String,

    @ColumnInfo(name = "imageuri")
    var imageUri: String,

    @ColumnInfo(name = "age")
    var age: Int,

    @ColumnInfo(name = "date")
    val date: LocalDateTime = LocalDateTime.now(),

    @Embedded
   var address: Address?
)
