package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.dp_80
import com.example.jetpack_compose_all_in_one.ui.theme.sp_14

@Composable
fun NetworkErrorDialog(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dp_10)
    ) {
        Column(
            modifier = Modifier.align(TopStart),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_signal_wifi_statusbar_connected_no_internet_4_24),
                    contentDescription = "logo",
                    Modifier.size(width = dp_80, height = dp_50)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Center)
                .offset(y = -50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_signal_wifi_statusbar_connected_no_internet_4_24),
                contentDescription = "logo",
                alignment = Center,
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = stringResource(id = R.string.no_internet_connected),
            )
            Text(
                text = stringResource(id = R.string.no_internet_connected),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = sp_14,
                    fontWeight = FontWeight.Thin,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = { }, Modifier.width(147.dp)

            ) {
                Text(
                    text = stringResource(id = R.string.try_again)
                )
            }
        }
    }
}