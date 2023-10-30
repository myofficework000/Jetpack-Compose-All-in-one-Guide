package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.R


    @Composable
    fun DemoUI9() {

        var search by remember {
            mutableStateOf("")
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {

            val (r1, r2, r3, r4, r5, r6, r7) = createRefs()

            val guideLineStart = createGuidelineFromStart(20.dp)
            val guideLineEnd = createGuidelineFromEnd(20.dp)

            Box(modifier = Modifier
                .padding(10.dp)
                .constrainAs(r1) {
                    start.linkTo(guideLineStart)
                    top.linkTo(parent.top)
                }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
            }


            Box(modifier = Modifier
                .padding(10.dp)
                .constrainAs(r2) {
                    end.linkTo(guideLineEnd)
                    top.linkTo(parent.top)
                }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_filter_alt_24),
                    contentDescription = "filter",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(30.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .constrainAs(r3) {
                        top.linkTo(r1.bottom)
                        start.linkTo(guideLineStart)
                    },
            ) {
                Text(
                    text = "Search",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .constrainAs(r4) {
                        top.linkTo(r3.bottom)
                        start.linkTo(guideLineStart)
                        end.linkTo(guideLineEnd)
                    },
            ) {

                TextField(
                    value = search,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                    onValueChange = { search = it },
                    placeholder = { Text(text = "Search") },
                    trailingIcon = {
                        IconButton(onClick = {
                            search = ""
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear text")
                        }
                    }
                )

            }
            Box(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 0.dp, 0.dp)
                    .constrainAs(r5) {
                        top.linkTo(r4.bottom)
                        start.linkTo(guideLineStart)
                        end.linkTo(guideLineEnd)
                    },
            ) {
                Chips()
            }
            Box(
                modifier = Modifier
                    .padding(15.dp, 20.dp, 0.dp, 0.dp)
                    .constrainAs(r6) {
                        top.linkTo(r5.bottom)
                        start.linkTo(guideLineStart)
                        end.linkTo(guideLineEnd)
                    },
            ) {
                ImageList()
            }


        }

    }

    @Preview
    @Composable
    fun Chips() {

        val list = listOf("Comedy", "Horror", "Action")

        Row {
            SelectedChip(text = "All")
            LazyRow(Modifier.fillMaxWidth()) {
                items(list) {
                    BorderedChip(text = it)
                }
            }
        }

    }

    @Composable
    fun SelectedChip(text: String) {
        Row {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Red)
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(text = text, color = Color.White)
            }
            Spacer(modifier = Modifier.size(10.dp))
        }

    }

    @Composable
    fun BorderedChip(text: String) {
        Row {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Red, RoundedCornerShape(16.dp))
                    .padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(text = text, color = Color.White)
            }

            Spacer(modifier = Modifier.size(10.dp))
        }

    }

    @Preview
    @Composable
    fun ImageList() {
        val list = listOf("Title", "Title", "Title", "Title")

        LazyColumn {
            items(list) {
                ImageRow(title = it, sub = it)
                Spacer(modifier = Modifier.size(10.dp))
            }

        }
    }

    @Composable
    fun ImageRow(title: String, sub: String) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Color.DarkGray)
        ) {

            val (r1, r2, r3, r4) = createRefs()
            val guideline = createGuidelineFromStart(0.8f)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Some content",
                modifier = Modifier
                    .background(color = Color.Cyan, shape = RoundedCornerShape(10))
                    .height(130.dp)
                    .width(220.dp)
                    .constrainAs(r1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
            )

            Box(
                modifier = Modifier
                    .background(Color.Gray, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .constrainAs(r2) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(guideline)
                    },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = sub,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }
            }

            Box(modifier = Modifier
                .padding(0.dp, 0.dp, 20.dp, 0.dp)
                .height(40.dp)
                .constrainAs(r4) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_star_outline_24),
                    contentDescription = "Mark",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Red, CircleShape)
                        .padding(4.dp)
                )
            }
        }
    }

