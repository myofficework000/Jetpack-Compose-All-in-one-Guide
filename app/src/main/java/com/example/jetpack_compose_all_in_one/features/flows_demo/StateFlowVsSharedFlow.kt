package com.example.jetpack_compose_all_in_one.features.flows_demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Composable
fun FeatureFlowContent() {
    val currentPage = rememberSaveable { mutableIntStateOf(0) }

    LogicPager(
        pageCount = 2,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.feature_flow_example)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> FlowDemoWithMultipleConsumer()
                1 -> StateFlowVsSharedFlow()
            }
        }
    }
}

@Composable
private fun StateFlowVsSharedFlow() {
    val flowsViewModel: FlowsViewModel = hiltViewModel()
    val state1 by flowsViewModel.stateFlow1.collectAsState()
    val state2 by flowsViewModel.sharedFlow1.collectAsState(0)

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
            SimpleTextButton("Start Flow") { flowsViewModel.flowLikeRiver() }
            SimpleTextButton("End Flow") { flowsViewModel.stopTheRiver() }
        }
    }
}

private fun Int.addNCapped(num: Int) = ((this + num) % 10).toString()