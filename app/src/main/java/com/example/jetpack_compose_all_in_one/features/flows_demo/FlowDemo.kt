package com.example.jetpack_compose_all_in_one.features.flows_demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30

@Composable
fun FlowDemo(
    vm: FlowsViewModel
) {
    val state1 by vm.stateFlow1.collectAsState()
    val state2 by vm.sharedFlow1.collectAsState(0)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("State Flow", fontWeight = FontWeight.Bold, color = Color.Black)

        Row {
            for (i in 0..9) Text(state1.addNCapped(i), color = Color.Black)
        }

        Spacer(Modifier.height(dp_15))

        Text("Shared Flow", fontWeight = FontWeight.Bold, color = Color.Black)

        Row {
            for (i in 0..9) Text(state2.addNCapped(i), color = Color.Black)
        }

        Spacer(Modifier.height(dp_30))

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly
        ) {
            SimpleTextButton("Start Flow") { vm.flowLikeRiver() }
            SimpleTextButton("End Flow") { vm.stopTheRiver() }
        }
    }
}

private fun Int.addNCapped(num: Int) = ((this + num) % 10).toString()