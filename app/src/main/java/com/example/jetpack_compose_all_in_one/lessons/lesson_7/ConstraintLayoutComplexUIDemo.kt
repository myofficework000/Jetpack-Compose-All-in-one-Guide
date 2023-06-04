package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.TextButton
import com.example.jetpack_compose_all_in_one.ui.theme.AqiGradient
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8


@Preview
@Composable
fun MovieDetailScreenUI() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (profile, settings) = createRefs()
        val (backgroundImage, posterImage, movieName) = createRefs()
        val (ratingValue, ratingBar, ratingLabel, gradeLabel, storyLine) = createRefs()

        val guidelineFromTop30Perc = createGuidelineFromTop(0.30f)
        val guidelineFromTop25Perc = createGuidelineFromTop(0.25f)
        val guidelineFromTop50Perc = createGuidelineFromTop(0.50f)
        val guidelineFromStart30Perc = createGuidelineFromStart(0.30f)

        createHorizontalChain(profile, settings, chainStyle = ChainStyle.SpreadInside)

        Box(modifier = Modifier
            .constrainAs(backgroundImage) {
                bottom.linkTo(guidelineFromTop30Perc)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .background(Color.Magenta)
            .fillMaxSize()
        )

        Image(
            painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.movie_bg_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                }
                .size(dp_50)
                .clip(CircleShape)
        )

        Image(
            painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = stringResource(id = R.string.movie_bg_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(settings) {
                    top.linkTo(parent.top)
                }
                .size(dp_50)
                .clip(CircleShape)
        )

        Image(
            painterResource(id = R.drawable.landscape3),
            contentDescription = stringResource(id = R.string.movie_bg_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(posterImage) {
                    top.linkTo(guidelineFromTop25Perc)
                    bottom.linkTo(storyLine.top)
                    start.linkTo(parent.start)
                    end.linkTo(guidelineFromStart30Perc)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .padding(start = dp_8)
        )

        Text(
            text = stringResource(id = R.string.movie_name),
            modifier = Modifier.constrainAs(movieName) {
                top.linkTo(guidelineFromTop30Perc)
                start.linkTo(guidelineFromStart30Perc)
            },
            color = Color.Black
        )

        Text(
            text = stringResource(id = R.string.story_line),
            modifier = Modifier.constrainAs(storyLine) {
                top.linkTo(guidelineFromTop50Perc)
                start.linkTo(parent.start)
            },
            color = Color.Black
        )

        Text(
            text = stringResource(id = R.string.rating_value),
            modifier = Modifier.constrainAs(ratingValue) {
                top.linkTo(movieName.bottom)
                start.linkTo(guidelineFromStart30Perc)
            },
            color = Color.Black
        )

        Text(
            text = stringResource(id = R.string.rating_lable),
            modifier = Modifier.constrainAs(ratingLabel) {
                top.linkTo(ratingValue.bottom)
                start.linkTo(guidelineFromStart30Perc)
            },
            color = Color.Black
        )

        Text(
            text = stringResource(id = R.string.grade_now),
            modifier = Modifier
                .constrainAs(gradeLabel) {
                    top.linkTo(guidelineFromTop30Perc)
                    start.linkTo(ratingValue.end)
                }
                .padding(start = 10.dp),
            color = Color.Yellow
        )
    }
}

@Composable
fun GetAPIWeatherUI(aqi: Int) {
    val aqiState = remember{ derivedStateOf {
        when (aqi) {
            1 -> "Good"
            2 -> "Fair"
            3 -> "Ok"
            4 -> "Unhealthy"
            5 -> "BRO GET INSIDE"
            else -> ""
        }
    } }

    Card(Modifier.fillMaxWidth()) {
        ConstraintLayout(Modifier.padding(horizontal = 16.dp)) {
            val (title, desc, bar, barKnot, seeMore) = createRefs()

            Text("Air quality: ${aqiState.value}", Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
            Text("Blah blah blah", Modifier.constrainAs(desc) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
            })
            Card(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(10.dp)
                    .fillMaxWidth()
                    .constrainAs(bar) {
                        top.linkTo(desc.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(AqiGradient)) {}
            }

            Icon(
                painterResource(R.drawable.baseline_dot_12),
                "",
                Modifier.constrainAs(barKnot) {
                    top.linkTo(bar.top)
                    bottom.linkTo(bar.bottom)
                    linkTo(bar.start, bar.end, bias = aqi / 5f)
                },
                tint = Color.White
            )

            TextButton("See More", Modifier
                .constrainAs(seeMore) {
                    top.linkTo(bar.bottom)
                    start.linkTo(parent.start)
                }
            ) {}
        }
    }
}

@Composable
fun GetMusicUI() {
//Alex
}

@Preview
@Composable
private fun GetAPIWeatherUIPreview() {
    GetAPIWeatherUI(4)
}

//Luan
@Composable
fun GetMovieScreenScreen() {

}