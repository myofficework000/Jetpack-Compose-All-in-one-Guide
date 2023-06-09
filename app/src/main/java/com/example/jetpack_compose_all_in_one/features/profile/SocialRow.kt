package com.example.jetpack_compose_all_in_one.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.BackgroundColor
import com.example.jetpack_compose_all_in_one.ui.theme.dp_1
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5

@Composable
fun SocialRow() {
    Card(
        modifier = Modifier
            .then(Modifier.padding(dp_5))
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(dp_1, BackgroundColor),
        elevation = CardDefaults.cardElevation(dp_5),
    ) {
        val context = LocalContext.current
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            IconButton(onClick = { launchSocialActivity(context, "github") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_github_square_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { launchSocialActivity(context, "twitter") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_twitter_square_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { launchSocialActivity(context, "linkedin") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_linkedin_brands),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
