package com.example.jetpack_compose_all_in_one.features.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests",
        style = typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        InterestTag("Android")
        InterestTag("Compose")
        InterestTag("Flutter")
        InterestTag("SwiftUI")
    }
    Row(modifier = Modifier.padding(start = 8.dp)) {
        InterestTag("Video games")
        InterestTag("Podcasts")
        InterestTag("Basketball")
    }
}
