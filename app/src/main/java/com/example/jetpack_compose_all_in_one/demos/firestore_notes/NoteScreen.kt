package com.example.jetpack_compose_all_in_one.demos.firestore_notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen(noteRepository: NoteRepository = NoteRepository()) {
    var notes by remember { mutableStateOf(listOf<Note>()) }
    var newNoteTitle by remember { mutableStateOf("") }
    var newNoteContent by remember { mutableStateOf("") }

    LaunchedEffect(key1 = "load_notes") {
        noteRepository.getNotes {
            notes = it
        }
    }

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = newNoteTitle,
            onValueChange = { newNoteTitle = it },
            label = { Text(text = "Title") }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = newNoteContent,
            onValueChange = { newNoteContent = it },
            label = { Text(text = "Content") }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
            noteRepository.addNote(Note(newNoteTitle, newNoteContent)) { success ->
                if (success) {
                    newNoteTitle = ""
                    newNoteContent = ""
                    noteRepository.getNotes {
                        notes = it
                    }
                }
            }
        }) {
            Text(text = "Add Note")
        }

        LazyColumn() {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Title: ${note.title}")
                        Text(text = "Content: ${note.content}")
                    }
                }
            }
        }
    }


}