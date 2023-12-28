package com.example.jetpack_compose_all_in_one.features.newsapi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.Article
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.viewmodel.NewsApiViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.utils.ResultState

@Composable
fun NewsUI(newsViewModel : NewsApiViewModel = viewModel()){

    val newsDataState by newsViewModel.news.collectAsState(initial = null)

    when(val result = newsDataState){
        is ResultState.Success -> {
            val articles = result.body?.articles ?: emptyList()
            LazyColumn {
                items(articles){ article ->
                    NewsItem(article = article)
                }
            }
        }
        is ResultState.Error -> {
            ResultState.Error<HeadlineResponse>(result.errorMessage)
        }
        is ResultState.Exception -> {}
        is ResultState.Loading -> {}
        null -> {}
    }

    LaunchedEffect(newsViewModel){
        newsViewModel.getHeadlinesNews()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(article: Article){
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(dp_8),
        shape = RoundedCornerShape(dp_8),
        elevation = dp_8,
        backgroundColor = Color.Transparent
        ) {
        Column(modifier = Modifier.padding(dp_16)
            .background(Color.Transparent)
        ) {
            Text(
                text = article.title,
                style = TextStyle(
                    fontSize = sp_16,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(dp_8))

            article.description?.let { Text(text = it) }
            Spacer(modifier = Modifier.height(dp_8))

            GlideImage(
                article.urlToImage,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            
            Row(horizontalArrangement = Arrangement.SpaceBetween){

                Text(text = article.author ?: "")
                
                Text(text = article.publishedAt)
            }

        }
    }
}