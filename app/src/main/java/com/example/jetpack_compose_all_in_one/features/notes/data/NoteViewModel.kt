package com.example.jetpack_compose_all_in_one.features.notes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notes : LiveData<List<Note>> = noteRepository.allNotes

    fun getAllNotes(){
        noteRepository.getAllNotes()
    }

    fun addNote(note: Note){
        noteRepository.addNote(note)
        getAllNotes()
    }

    fun updateNote(note: Note){
        noteRepository.updateNote(note)
        getAllNotes()
    }

    fun deleteNote(note: Note){
        noteRepository.deleteNote(note)
    }

}