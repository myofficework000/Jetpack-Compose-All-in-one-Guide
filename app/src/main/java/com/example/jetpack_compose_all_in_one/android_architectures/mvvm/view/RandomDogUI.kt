package com.example.jetpack_compose_all_in_one.android_architectures.mvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.viewmodel.DogViewModel
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RandomDogUI(
    vm: DogViewModel
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            vm.dogFluff.url,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.5f)
        )
        SimpleTextButton("MORE FLUFF") {
            vm.getDogImageFromApi()
        }
    }
}