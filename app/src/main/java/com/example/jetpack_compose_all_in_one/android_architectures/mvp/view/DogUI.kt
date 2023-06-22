package com.example.jetpack_compose_all_in_one.android_architectures.mvp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.android_architectures.mvp.model.DogApiService
import com.example.jetpack_compose_all_in_one.android_architectures.mvp.model.DogRepository
import com.example.jetpack_compose_all_in_one.android_architectures.mvp.presenter.DogMVP
import com.example.jetpack_compose_all_in_one.android_architectures.mvp.presenter.DogPresenter
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.utils.Constants.RANDOM_WOOF_BASE_URL
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun DogMvpUI(){
    val retrofit = Retrofit.Builder()
        .baseUrl(RANDOM_WOOF_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(DogApiService::class.java)
    val dogRepository = DogRepository(apiService)

    val dogImageState = remember { mutableStateOf("") }
    val isLoadingState = remember { mutableStateOf(false)}
    val errorState = remember { mutableStateOf("")}

    val dogView = object : DogMVP.DogView {
        override fun setResult(url: String) {
            dogImageState.value = url
            isLoadingState.value = false
            errorState.value = ""
        }

        override fun onLoad(isLoading: Boolean) {
            isLoadingState.value = isLoading
        }

        override fun showError(message: String) {
            errorState.value = message
        }
    }

    val dogPresenter = DogPresenter(dogRepository, dogView)
    
    LaunchedEffect(Unit){
        dogPresenter.attachView(dogView)
        dogPresenter.getDog()
    }

    DogScreen(
        dogImageState.value,
        isLoadingState.value,
        errorState.value,
        dogPresenter
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DogScreen(
    dogImageUrl: String,
    isLoading: Boolean,
    error: String,
    presenter: DogPresenter){

    val scope = rememberCoroutineScope()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            dogImageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.5f)
        )
        if (isLoading){
            CircularProgressIndicator()
        } else if (error.isNotEmpty()){
            Text(text = error, color = Color.Red)
        } else {
            SimpleTextButton("MORE FLUFF") {
                scope.launch {
                    presenter.getDog()
                }
            }
        }

    }
}
