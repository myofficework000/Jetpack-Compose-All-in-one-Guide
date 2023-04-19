package com.example.jetpack_compose_all_in_one.ui.views.tmdbapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponseItem
import com.example.jetpack_compose_all_in_one.utils.Constants

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
    data: TmdbResponseItem
) {
    Card() {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                Constants.tmdb_image_base_url + data.posterPath, "",
                Modifier.fillMaxWidth(0.5f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(data.title, fontSize = 24.sp)
        }
    }
}