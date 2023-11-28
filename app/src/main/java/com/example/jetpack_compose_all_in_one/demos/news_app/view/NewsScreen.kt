package com.example.jetpack_compose_all_in_one.demos.news_app.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.demos.news_app.intent.RemoteNewsIntent
import com.example.jetpack_compose_all_in_one.demos.news_app.model.models.Article
import com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel.RemoteNewsState
import com.example.jetpack_compose_all_in_one.demos.news_app.model.viewmodel.RemoteNewsViewModel

@Composable
fun NewsScreen(viewModel: RemoteNewsViewModel) {

    val state by viewModel.state
    var isLoading by remember {
        mutableStateOf(true)
    }

    // Get the list of news from the state
    val newsList = when (state) {
        is RemoteNewsState.NewsList -> (state as RemoteNewsState.NewsList).list
        else -> emptyList()
    }

    // Remember the list so that it survives recomposition
    val rememberedNewsList = remember { mutableStateListOf(*newsList.toTypedArray()) }


    when (state) {
        is RemoteNewsState.Loading -> {
            Log.e("Tag", "... loading..")
            // show loading
            isLoading = (state as RemoteNewsState.Loading).isLoading
        }

        is RemoteNewsState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (state as RemoteNewsState.Error).error,
                Toast.LENGTH_LONG
            ).show()
            Log.e("Tag", "...error" + (state as RemoteNewsState.Error).error)

            // hide loading
            isLoading = false
        }

        RemoteNewsState.Idle -> {
            Log.e("Tag", "...idle")
            isLoading = true
        }

        is RemoteNewsState.NewsList -> {
            // Update the remembered list when the state changes
            rememberedNewsList.clear()
            rememberedNewsList.addAll(newsList)
            Log.e("Tag", "... list" + (state as RemoteNewsState.NewsList).list.joinToString())

            // hide loading
            isLoading = false
        }
    }

    viewModel.processIntent(RemoteNewsIntent.GetNewsIntent)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                viewModel.processIntent(RemoteNewsIntent.GetNewsIntent)
            }

    ) {
        NewsList(newsList = rememberedNewsList)
        LoadingIndicator(isLoading)
    }

}


@Composable
fun NewsList(newsList: SnapshotStateList<Article>) {
    LazyColumn {
        items(newsList) {
            NewsRow(news = it)
        }
    }
}

@Composable
fun NewsRow(news: Article) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Cyan, RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(text = news.title, fontWeight = FontWeight.Bold)
            Text(text = news.content, fontStyle = FontStyle.Italic)

        }
    }

}

@Composable
fun LoadingIndicator(isLoading: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray.copy(alpha = 0.1f))
                    .padding(4.dp)
            )
        }
    }
}