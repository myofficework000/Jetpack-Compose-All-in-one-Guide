package com.example.jetpack_compose_all_in_one.features.notes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("Select * from my_notes")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note :Note)
}