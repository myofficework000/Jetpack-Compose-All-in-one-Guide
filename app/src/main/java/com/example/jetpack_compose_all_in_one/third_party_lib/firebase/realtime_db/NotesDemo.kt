package com.example.jetpack_compose_all_in_one.third_party_lib.firebase.realtime_db

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date


private lateinit var databaseRef: DatabaseReference
private var noteList = mutableStateListOf<Note>()

@Preview
@Composable
fun NotesDemo() {

    setUpDataBase()

    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            //
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                noteList.clear()

                for (child in snapshot.children) {
                    val note = child.getValue(Note::class.java)
                    note?.let {
                        noteList.add(note)
                    }
                }
                Log.e("Notes", noteList.size.toString())
            }
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        EnterNoteUI()
        Spacer(modifier = Modifier.size(10.dp))
        NoteList()
    }
}


@Preview
@Composable
fun NoteList() {

    println("Notes")
    println(noteList.joinToString())
    LazyColumn {
        items(noteList.reversed()) { NoteItem(note = it) }
    }

}


@Composable
fun NoteItem(note: Note) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(12.dp))
                .padding(10.dp)
        ) {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = note.title, Modifier.weight(1f)
                    )
                    Text(
                        text = note.date, Modifier
                            .weight(0.5f)
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = note.description, Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
    Spacer(modifier = Modifier.size(10.dp))


}

@Preview
@Composable
fun EnterNoteUI() {
    val context = LocalContext.current

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan, RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Column {
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Note title") },
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Note description") },
            )
            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = {
                    if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
                        toast(context, "Please enter a title and description for notes")
                    } else {
                        saveNote(context, Note(title, description, "date"))
                    }
                }, Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Save note")
            }
        }


    }


}

fun setUpDataBase() {
    databaseRef = FirebaseDatabase.getInstance().getReference("Notes")
}

data class Note(var title: String = "", var description: String = "", var date: String = "")

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}


fun saveNote(context: Context, note: Note) {
    val sdf = SimpleDateFormat("dd/MMM/yyyy")
    val date = sdf.format(Date())
    note.date = date
    val noteId = databaseRef.push().key.toString()

    databaseRef.child(noteId).setValue(note).addOnCompleteListener {
        toast(context, "Note added successfully")
    }
}