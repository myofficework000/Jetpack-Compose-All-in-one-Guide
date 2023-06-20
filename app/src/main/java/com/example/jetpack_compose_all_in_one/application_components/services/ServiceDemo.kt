package com.example.jetpack_compose_all_in_one.application_components.services

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.application_components.content_provider.custom_content_provider.CustomContentProvider
import com.example.jetpack_compose_all_in_one.application_components.content_provider.demo_images.ShowImages
import com.example.jetpack_compose_all_in_one.application_components.services.counter.CounterAppWithService
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Composable
private fun ServiceContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 2,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.service_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> CounterAppWithService()
                1 -> ShowImages(9)
                2 -> CustomContentProvider()
            }
        }
    }
}
