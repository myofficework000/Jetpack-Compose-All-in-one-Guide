package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.presentation.ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.presentation.viewmodel.DogViewModelCleanCode
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DogApiMainPage(
    vm: DogViewModelCleanCode = hiltViewModel()
) {
    var imageLoading by remember{ mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        vm.dogResponse?.let{
            Box(contentAlignment = Alignment.Center) {
                if (imageLoading) {
                    Box(
                        modifier = Modifier.size(400.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(Modifier.size(128.dp))
                    }
                }

                GlideImage(
                    model = it.message,
                    contentDescription = "woof",
                    modifier = Modifier.size(400.dp)
                ) { it2 ->
                    it2.addListener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageLoading = false
                            return true
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            imageLoading = false
                            return false
                        }
                    })
                }
            }
        }

        SimpleTextButton(buttonMessage = "Woof again!") {
            imageLoading = true
            vm.getRandomDog()
        }
    }
}