package com.example.jetpack_compose_all_in_one.demos.firestore_notes

import com.google.firebase.firestore.FirebaseFirestore

class NoteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val notesCollection = db.collection("notes")

    fun getNotes(callback: (List<Note>) -> Unit) {
        notesCollection.get()
            .addOnSuccessListener { result ->
                val notes = result.toObjects(Note::class.java)
                callback(notes)
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }

    fun addNote(note: Note, callback: (Boolean) -> Unit) {
        notesCollection.add(note)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
}