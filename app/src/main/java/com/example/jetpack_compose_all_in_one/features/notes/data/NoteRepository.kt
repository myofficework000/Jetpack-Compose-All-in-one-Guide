package com.example.jetpack_compose_all_in_one.features.notes.data

import androidx.lifecycle.MutableLiveData


class NoteRepository(private val noteDao : NoteDao) {

    val allNotes = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        noteDao.getAllNotes()
    }

    fun addNote(note: Note){
        noteDao.insertNote(note)
    }

    fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}