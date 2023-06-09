package com.example.jetpack_compose_all_in_one.features.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoreInfoSection() {
    val context = LocalContext.current
    Text(
        text = "More Info",
        style = typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    ListItem(
        icon = {
            /*Icon(
                painter = painterResource(id = R.),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )*/
        },
        text = {
            Text(
                text = "Jetpack Compose All in One",
                style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to checkout the repo for the project") },
        modifier = Modifier
            .clickable(onClick = { launchSocialActivity(context, "repository") })
    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Email, contentDescription = null) },
        text = {
            Text(
                text = "Contact Me",
                style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to write me about any concern or info at $email") },
        modifier = Modifier
            .clickable(onClick = { launchSocialActivity(context, "repository") })
    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Settings, contentDescription = null) },
        text = {
            Text(
                text = "Demo Settings",
                style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Not included yet. coming soon..") },
        modifier = Modifier.clickable(onClick = {})
    )
}
