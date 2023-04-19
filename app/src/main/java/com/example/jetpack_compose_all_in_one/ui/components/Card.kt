package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTextWithIcon(country: Country, onRemoveClicked: (country: Country) -> Unit) {
    Card(
        shape = RoundedCornerShape(dp_4),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = country.name, fontWeight = FontWeight.Bold)
            IconButton(
                modifier = Modifier
                    .size(50.dp),
                onClick = {
                    onRemoveClicked(country)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.close_desc)
                )
            }
        }
    }

}