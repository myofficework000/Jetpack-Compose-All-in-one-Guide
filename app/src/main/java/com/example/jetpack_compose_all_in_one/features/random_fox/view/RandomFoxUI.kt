package com.example.jetpack_compose_all_in_one.features.random_fox.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.random_fox.viewmodel.RandomFoxViewModel
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.BackgroundColor
import com.example.jetpack_compose_all_in_one.ui.theme.dp_1
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_150
import com.example.jetpack_compose_all_in_one.ui.theme.dp_200
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5

@Composable
fun RandomFoxUI() {
    val viewModel: RandomFoxViewModel = hiltViewModel()
    Column {
        ShowRandomFoxUsingCoroutines(viewModel)
        ShowRandomFoxUsingRxJava(viewModel)
        ShowRandomFoxUsingFlow(viewModel)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowRandomFoxUsingCoroutines(vm: RandomFoxViewModel) = with(vm) {
    val response = foxStateCoroutines.observeAsState()

    Card(
        modifier = Modifier
            .then(Modifier.padding(dp_5))
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(dp_1, BackgroundColor),
        elevation = CardDefaults.cardElevation(dp_5),
    ) {
        Column(
            Modifier.size(dp_150, dp_200),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                response.value?.image,
                contentDescription = stringResource(id = R.string.next_random_dog_coroutines),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
            )
            SimpleTextButton(stringResource(id = R.string.next_random_dog_coroutines)) {
                fetchRandomFoxUsingCoroutines()
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowRandomFoxUsingRxJava(vm: RandomFoxViewModel) = with(vm) {
    val response = foxStateRxJava.observeAsState()

    Card(
        modifier = Modifier
            .then(Modifier.padding(dp_5))
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(dp_1, BackgroundColor),
        elevation = CardDefaults.cardElevation(dp_5),
    ) {
        Column(
            Modifier.size(dp_150, dp_200),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                response.value?.image,
                contentDescription = stringResource(id = R.string.next_random_dog_rx),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.5f)
            )
            SimpleTextButton(stringResource(id = R.string.next_random_dog_rx)) {
                fetchRandomFoxUsingRxJava()
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowRandomFoxUsingFlow(vm: RandomFoxViewModel) = with(vm) {
    val response = foxStateFlow.collectAsState()

    Card(
        modifier = Modifier
            .then(Modifier.padding(dp_5))
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(dp_1, BackgroundColor),
        elevation = CardDefaults.cardElevation(dp_5),
    ) {
        Column(
            Modifier.size(dp_150, dp_200),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (response.value.image?.isNotEmpty() == true) {
                GlideImage(
                    response.value.image,
                    contentDescription = stringResource(id = R.string.next_random_dog_flows),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f)
                )
            }
            SimpleTextButton(stringResource(id = R.string.next_random_dog_flows)) {
                fetchRandomFoxUsingFlow()
            }
        }
    }
}