package com.example.jetpack_compose_all_in_one.android_architectures.mvi.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.android_architectures.mvi.resource.Resource
import com.example.jetpack_compose_all_in_one.android_architectures.mvi.viewModel.RandomDogViewModel
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.utils.showLongToast
import com.example.jetpack_compose_all_in_one.R

@Composable
fun RandomDogView(
) {

    val viewModel: RandomDogViewModel = hiltViewModel()
    val state by viewModel.viewState.collectAsState()

    when (state) {
        is Resource.Loading -> DisplayProgressBar()
        is Resource.Success -> ShowData(state.data.message)
        is Resource.Failure -> showLongToast(LocalContext.current, stringResource(id = R.string.generic_error))
        else -> {}
    }

    SimpleTextButton("Click to Refresh") {
        viewModel.triggerAction(RandomDogViewModel.Action.LoadRandomDog)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowData(info: String) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            info,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.5f)
        )
    }
}

@Composable
fun DisplayProgressBar() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.Center)
            .size(100.dp))
    }

}
