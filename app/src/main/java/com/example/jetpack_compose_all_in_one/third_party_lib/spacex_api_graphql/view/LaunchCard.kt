package com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.GetLaunchesPastQuery
import com.example.jetpack_compose_all_in_one.utils.isoDateToLocal

@Composable
fun LaunchCard(
    item: GetLaunchesPastQuery.LaunchesPast
) {
    Card(shape = RoundedCornerShape(32.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0.1f, 0.1f, 0.5f, 0.4f))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(item.mission_name, style = MaterialTheme.typography.h5)
            Text(
                item.details ?: "(No details provided)",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text("Rocket: ${item.rocket.rocket_name}")
            Text("Launched at: ${item.launch_date_utc.isoDateToLocal()}")
        }
    }
}