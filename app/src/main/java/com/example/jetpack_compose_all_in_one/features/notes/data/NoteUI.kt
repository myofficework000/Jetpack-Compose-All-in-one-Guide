package com.example.jetpack_compose_all_in_one.features.notes.data

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun NoteUI(context: Context){
    val noteViewModel : NoteViewModel = remember { initViewModel(context) }
    val showAddNoteUI = remember { mutableStateOf(false) }
    val showNoteList = remember { mutableStateOf(false) }
    Column( modifier = Modifier.fillMaxSize()) {
        Button(onClick = { showAddNoteUI.value = true }) {
            Text(text = "Add Note")
        }

        Button(onClick = { showNoteList.value = true }) {
            Text(text = "Show Notes")
        }

        if(showAddNoteUI.value){
            AddNoteUI(
                noteViewModel = noteViewModel,
                onNoteAdded = {showAddNoteUI.value = false}
            )
        }
        if(showNoteList.value){
            NoteList(noteViewModel = noteViewModel)
        }
    }
}

@Composable
fun NoteItem(note: Note){
    Card( modifier = Modifier
        .fillMaxWidth()
    ) {
        Column(modifier  = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.body, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun NoteList(
    noteViewModel: NoteViewModel
){
    val notes by noteViewModel.notes.observeAsState(emptyList())

    Box( modifier =  Modifier.fillMaxSize()){

        LazyColumn(modifier = Modifier.wrapContentHeight()){
            items(notes){ note ->
                NoteItem(note = note)
            }
        }
    }
}

@Composable
fun AddNoteUI(noteViewModel: NoteViewModel, onNoteAdded: () -> Unit){
    val noteTitle = remember { mutableStateOf(TextFieldValue())}
    val noteBody = remember { mutableStateOf(TextFieldValue())}
    Column( modifier = Modifier.fillMaxSize()) {

        TextField(
            label = { Text(text = "Title")},
            value = noteTitle.value ,
            onValueChange = {noteTitle.value = it },
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()

        )
        TextField(
            label = { Text(text = "")},
            value = noteBody.value ,
            onValueChange = {noteBody.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    val note = Note(
                        itemId = 0,
                        title = noteTitle.value.text,
                        body = noteBody.value.text
                    )
                    noteViewModel.addNote(note)
                    onNoteAdded.invoke()
                    noteTitle.value = TextFieldValue()
                    noteBody.value = TextFieldValue()
                          },
            ) {
                Text(text = "Done")
            }
        }
    }
}

private fun initViewModel(context: Context) : NoteViewModel{
    val database = NoteDatabase.getInstance(context)
    val noteDao = database.noteDao()
    val repository = NoteRepository(noteDao)

    return NoteViewModel(repository)
}
