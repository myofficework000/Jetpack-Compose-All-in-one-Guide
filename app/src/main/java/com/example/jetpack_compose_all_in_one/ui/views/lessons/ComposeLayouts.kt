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
import androidx.compose.ui.unit.sp
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
                Modifier.background(L1BoxColor3).size(200.dp)
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
            Text("This is a box that with aligning")
            Box(
                Modifier.background(L1BoxColor3).size(200.dp)
            ) {
                Text("1", Modifier.align(Alignment.TopStart))
                Text("2", Modifier.align(Alignment.TopEnd))
                Text("3", Modifier.align(Alignment.BottomStart))
                Text("4", Modifier.align(Alignment.BottomCenter))
                Text("(R.I.P. OCD)", Modifier.align(Alignment.BottomEnd), fontSize = 4.sp)
                Text("5", Modifier.align(Alignment.Center))
            }
        }
    }
}