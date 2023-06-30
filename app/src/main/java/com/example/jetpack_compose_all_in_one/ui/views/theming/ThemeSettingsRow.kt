package com.example.jetpack_compose_all_in_one.ui.views.theming

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.jetpack_compose_all_in_one.R

@Composable
fun ThemeSettingsRow(
    vm: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { vm.requestDialog() }
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Current Theme: ")

        Row {
            Text(vm.themeMode.toLabel())
            Icon(
                painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = ""
            )
        }
    }
}