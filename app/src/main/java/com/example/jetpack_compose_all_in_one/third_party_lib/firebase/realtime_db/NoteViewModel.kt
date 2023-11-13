package com.example.jetpack_compose_all_in_one.third_party_lib.firebase.realtime_db

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note2>>(emptyList())
    val notes: StateFlow<List<Note2>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        repository.getNoteUpdates { note ->
            _notes.value = note
        }
    }

    fun sendNote(title: String, date: String, content: String) {
        val note = Note2(title, date, content)
        repository.saveNote(note)
    }
}