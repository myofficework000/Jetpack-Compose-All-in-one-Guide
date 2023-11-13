package com.example.jetpack_compose_all_in_one.third_party_lib.firebase.realtime_db

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class NoteRepository {

    private val database = FirebaseDatabase.getInstance().getReference("notes")

    fun saveNote(note: Note2) {
        val noteId = database.push().key
        noteId?.let {
            database.child(it).setValue(note)
        }
    }

    fun getNoteUpdates(action: (List<Note2>) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val note = snapshot.children.mapNotNull { it.getValue<Note2>() }
                action(note)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("MessageRepo", "Failed to read notes", error.toException())
            }
        })
    }

}