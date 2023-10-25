package com.example.jetpack_compose_all_in_one.demos.tiktok.components.discovers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme

@Composable
fun DiscoverScreen() {
    JetpackComposeAllInOneTheme(darkTheme = false) {
        Surface {
            Column {
                SearchSection()
                LanesSection()
                Spacer(modifier = Modifier.height(400.dp))
            }
        }
    }
}
