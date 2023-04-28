package com.example.jetpack_compose_all_in_one.ui.views.lessons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn
import com.example.jetpack_compose_all_in_one.ui.theme.L1BoxColor1
import com.example.jetpack_compose_all_in_one.ui.theme.L1BoxColor2
import com.example.jetpack_compose_all_in_one.ui.theme.L1BoxColor3

@Composable
fun ComposeLayouts() {
    ScrollableColumn(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is a column")
            Column(
                Modifier.background(L1BoxColor1)
            ) {
                Text("1")
                Text("2")
                Text("3")
                Text("4")
                Text("5")
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is a row")
            Row(
                Modifier.background(L1BoxColor2)
            ) {
                Text("1")
                Text("2")
                Text("3")
                Text("4")
                Text("5")
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is a box that with no aligning")
            Box(
                Modifier
                    .background(L1BoxColor1)
                    .size(200.dp)
            ) {
                Box(
                    Modifier
                        .background(L1BoxColor2)
                        .size(150.dp)
                ) {
                    Box(
                        Modifier
                            .background(L1BoxColor3)
                            .size(50.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is a box that with aligning with Parent")
            Box(
                Modifier
                    .background(L1BoxColor3)
                    .size(250.dp)
            ) {
                Text("Center", Modifier.align(Alignment.Center))
                Text("Top Start", Modifier.align(Alignment.TopStart))
                Text("Top End", Modifier.align(Alignment.TopEnd))
                Text("Bottom Start", Modifier.align(Alignment.BottomStart))
                Text("Bottom End", Modifier.align(Alignment.BottomEnd))
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is a box that with aligning with Parent Edges and center")
            Box(
                Modifier
                    .background(L1BoxColor3)
                    .size(250.dp)
            ) {
                Text("Top Center", Modifier.align(Alignment.TopCenter))
                Text("Center Start", Modifier.align(Alignment.CenterStart))
                Text("Center End", Modifier.align(Alignment.CenterEnd))
                Text("Bottom Center", Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}