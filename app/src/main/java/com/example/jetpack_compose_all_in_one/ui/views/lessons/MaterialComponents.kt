package com.example.jetpack_compose_all_in_one.ui.views.lessons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn

@Composable
fun MaterialComponents() {
    ScrollableColumn(
        Modifier.fillMaxSize()
    ) {
        var deezFields by remember{ mutableStateOf("") }

        Column{
            Text("Text Component:")
            Text("This is text: Text(\"This is text: Text(\"This is text: <bruh stop>\")\")")
        }

        Spacer(Modifier.height(32.dp))

        Column{
            Text("Button Component:")
            Button({}) {Text("Button({whateverFunctionCalls()}) {Text(\"No recursions\")}")}
        }

        Spacer(Modifier.height(32.dp))

        Column{
            Text("InputField Component:")
            TextField(
                deezFields,
                onValueChange = { deezFields = it },
                label={Text("TextField(deezFields, onValueChange = { deezFields = it }, label={ Text(\"I said no.\") })")})
            Text("Remember to add a state.")
            Text("var deezFields by remember{ mutableStateOf(\"\") }")
        }
    }
}