package com.example.jetpack_compose_all_in_one.lessons.lesson_19

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun DogImageScreen(viewModel: DogViewModel = hiltViewModel()) {
    val dogUrl by viewModel.dogImage.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        dogUrl?.let { url ->
            Image(
                modifier = Modifier.height(300.dp).fillMaxWidth(),
                painter = rememberImagePainter(data = dogUrl),
                contentDescription = "Dog Image"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.getRandomDogImage() }) {
            Text("Fetch Dog Image")
        }
    }
}