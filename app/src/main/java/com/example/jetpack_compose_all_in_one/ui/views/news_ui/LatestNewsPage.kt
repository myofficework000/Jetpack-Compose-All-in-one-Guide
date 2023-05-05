package com.example.jetpack_compose_all_in_one.ui.views.news_ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse

@Composable
fun LatestNewsPage(
    viewModel : NewsViewModel = hiltViewModel()
) {
    val result by viewModel.latestNewsResponse.observeAsState()

    Log.d("LatestNewsPage" , "Latest news result: $result")

    LaunchedEffect(result){
        viewModel.getLatestNews()
    }

    result?.let {latestNewsResponse ->
        Column {
            Text(text = "Latest News")
            Spacer(modifier = Modifier.height(16.dp))
            when(latestNewsResponse.status){
                "ok" -> NewsList(newsData = latestNewsResponse)
                else -> Text(text = "Error: ${latestNewsResponse.status}")
            }
        }
    } ?: Text(text = "Loading")
}

@Composable
fun NewsList(
    newsData : LatestNewsResponse,
) {

    Column( horizontalAlignment =  Alignment.CenterHorizontally) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(newsData.news.size){
                NewsCard(news = newsData.news[it])
            }
        }
    }
}