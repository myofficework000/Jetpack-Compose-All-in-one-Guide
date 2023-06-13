package com.example.jetpack_compose_all_in_one.features.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    var itemId: Long = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "body")
    var body: String
)
