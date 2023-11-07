package com.example.jetpack_compose_all_in_one.third_party_lib.firebase.realtime_db

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState()

    Column {
        NoteList2(notes = notes)
        UserInput {title, date, content ->
            viewModel.sendNote(title, date, content)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(onNoteSent: (String, String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column {
        TextField(
            value = title,
            onValueChange = {title = it},
            label = { Text(text = "Title")}
        )
        TextField(
            value = date,
            onValueChange = {date = it},
            label = { Text(text = "Date")}
        )
        TextField(
            value = content,
            onValueChange = {content = it},
            label = { Text(text = "Content")}
        )
        Button(onClick = {
            onNoteSent(title, date, content)
        }) {
            Text(text = "Save")
        }
    }
}

@Composable
fun NoteList2(notes: List<Note2>) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note)
        }
    }
}

@Composable
fun NoteItem(note: Note2) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(text = "Title: ${note.title.orEmpty()}")
        Text(text = "Date: ${note.date.orEmpty()}")
        Text(text = note.title.orEmpty())
    }
}
