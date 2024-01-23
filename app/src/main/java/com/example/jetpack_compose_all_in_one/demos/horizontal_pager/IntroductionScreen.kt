package com.example.jetpack_compose_all_in_one.demos.horizontal_pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroductionScreen() {
    val images = listOf(
        ImageData(R.drawable.landscape3, "Image 1"),
        ImageData(R.drawable.landscape4, "Image 2"),
        ImageData(R.drawable.landscape5, "Image 3"),
        ImageData(R.drawable.landscape6, "Image 4"),
    )
    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Image(
                painter = painterResource(id = images[page].imageRes), 
                contentDescription = null
            )
            Text(
                text = images[page].description,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        DotsIndicator(pagerState = pagerState, totalDots = images.size)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Skip")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        val nextPage = (pagerState.currentPage + 1) % images.size
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            ) {
                Text(text = "Next")
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotsIndicator(pagerState: PagerState, totalDots: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            for (i in 0 until totalDots) {
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = "Page indicator",
                    modifier = Modifier
                        .size(12.dp)
                        .padding(horizontal = 4.dp),
                    tint = if (pagerState.currentPage == i) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }
        }
    }
}
