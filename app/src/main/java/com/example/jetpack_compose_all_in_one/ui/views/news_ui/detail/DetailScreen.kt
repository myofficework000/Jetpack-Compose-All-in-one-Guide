package com.example.jetpack_compose_all_in_one.ui.views.news_ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_200
import com.example.jetpack_compose_all_in_one.ui.theme.sp_14
import com.example.jetpack_compose_all_in_one.ui.views.news_ui.dummyNewsItem

@Composable
fun DetailScreen(
    newsItem: News
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Black,
                shape = RectangleShape
            )
            .background(color = Color.Yellow)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {

            Text(
                text = newsItem.title,
                lineHeight = 28.sp,
                fontFamily = FontFamily.Cursive,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = newsItem.published,
                lineHeight = 18.sp,
                fontSize = sp_14,
                color = Color.Gray,
                fontFamily = FontFamily.Monospace
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = newsItem.author,
                color = Black,
                lineHeight = 18.sp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp_200)
                    .padding(top = dp_10),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = newsItem.image)
                    .build(),
                colorFilter = ColorFilter.colorMatrix(matrix),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = newsItem.description,
                color = Black,
                lineHeight = 22.sp,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            ),
            shape = RectangleShape,
            onClick = {

            }) {
            Text(
                text = "stringResource(R.string.read_full_article)",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailScreen(newsItem = dummyNewsItem)
}