package com.example.jetpack_compose_all_in_one.ui.views.news_ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_2
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.sp_12
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(
    news: News,
    onItemClick: (News) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .height(200.dp)
            .clickable { onItemClick(news) },
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(0.5.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(dp_10),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(Modifier.padding(dp_2)) {
                GlideImage(
                    model = news.image, contentDescription = "",
                    Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.7f)
                        .padding(dp_5)
                )

                Spacer(Modifier.padding(dp_2))

                Column(Modifier.padding(dp_2)) {
                    Text(
                        text = news.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = sp_16,
                        maxLines = 3,
                        overflow = TextOverflow.Clip
                    )
                    Text(
                        text = news.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = sp_16,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dp_5),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = news.author,
                    fontSize = sp_12,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    text = dateFormatter(news.published),
                    fontSize = sp_12,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }
    }
}

fun dateFormatter(date: String?): String {
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss +0000")
    val formattedDate = LocalDateTime.parse(date, dateFormat)
    return DateTimeFormatter.ofPattern("MMMM dd, yy | hh:mma").format(formattedDate)
}