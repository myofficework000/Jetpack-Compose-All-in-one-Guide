package com.example.jetpack_compose_all_in_one.ui.views.news_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.sp_10
import com.example.jetpack_compose_all_in_one.ui.theme.sp_12
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
    news: News
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dp_100),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(0.5.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(dp_10),
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(model = news.image , contentDescription = "" ,
                Modifier.fillMaxWidth(0.3f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp_5, bottom = dp_5),
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = news.author,
                    fontSize = sp_12,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    text = news.published,
                    fontSize = sp_12,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = news.title,
                fontWeight = FontWeight.Bold,
                fontSize = sp_16,
                textAlign = TextAlign.Center
            )
        }
    }
}