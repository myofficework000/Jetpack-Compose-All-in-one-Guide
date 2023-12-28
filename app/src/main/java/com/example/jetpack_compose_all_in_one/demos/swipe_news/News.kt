package com.example.jetpack_compose_all_in_one.demos.swipe_news

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.Article
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.viewmodel.NewsApiViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16
import com.example.jetpack_compose_all_in_one.ui.theme.dp_200
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.utils.ResultState


@Composable
fun News(newsViewModel: NewsApiViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        newsViewModel.getHeadlinesNews()
    }

    val context = LocalContext.current
    val newsDataState by newsViewModel.news.collectAsState()

    when (val result = newsDataState) {
        is ResultState.Success -> {
            DisplaySwipeableItems(result.body?.articles ?: emptyList())
        }

        is ResultState.Error -> {
            ResultState.Error<HeadlineResponse>(result.errorMessage)
        }

        is ResultState.Exception -> {
            try {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    }
                }
            } catch (e: Exception)
            {
                ResultState.Exception<HeadlineResponse>(e)
            }
        }

        is ResultState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        else -> {}
    }
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun DisplaySwipeableItems(
    articles: List<Article>,
    modifier: Modifier = Modifier,
) {

    val pageState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f
    ) { articles.size }

    HorizontalPager(state = pageState) { page ->
        Card(
            onClick = { /*TODO*/ },
            modifier = modifier
                .padding(dp_8)
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(dp_8),
            elevation = dp_8,
            backgroundColor = Color.Transparent,
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(dp_16)
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = articles[page].title,
                    style = TextStyle(
                        fontSize = sp_16,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = modifier.height(dp_8))

                articles[page].description?.let { Text(text = it) }
                Spacer(modifier = modifier.height(dp_8))

                GlideImage(
                    alignment = Alignment.Center,
                    model = if (articles[page].urlToImage.isNullOrBlank()) {
                        R.drawable.baseline_download_24
                    } else {
                        articles[page].urlToImage
                    },
                    contentDescription = "",
                    modifier = modifier
                        .size(dp_200),
                    contentScale = ContentScale.Crop
                )

                Row(horizontalArrangement = Arrangement.spacedBy(dp_8)) {
                    Text(text = articles[page].author ?: "Unknown")
                    Text(text = articles[page].publishedAt)
                }
            }
        }
    }
}
