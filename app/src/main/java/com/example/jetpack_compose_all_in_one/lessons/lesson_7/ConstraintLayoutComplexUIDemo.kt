package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32


@Preview
@Composable
fun MovieDetailScreenUI() {
    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color.White)) {
        val guidelineFromTop30Perc = createGuidelineFromTop(0.30f)
        val guidelineFromTop25Perc = createGuidelineFromTop(0.25f)
        val guidelineFromTop50Perc = createGuidelineFromTop(0.50f)
        val guidelineFromStart30Perc = createGuidelineFromStart(0.30f)

        val (backgroundImage, posterImage, movieName) = createRefs()
        val (ratingValue, ratingBar, ratingLabel, gradleLabel) = createRefs()

        Image(
            painterResource(id = R.drawable.landscape3),
            contentDescription = stringResource(id = R.string.movie_bg_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(backgroundImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guidelineFromTop30Perc)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        )

        Image(
            painterResource(id = R.drawable.landscape10),
            contentDescription = stringResource(id = R.string.movie_bg_image),
            modifier = Modifier
                .constrainAs(posterImage) {
                    top.linkTo(guidelineFromTop25Perc)
                    bottom.linkTo(guidelineFromTop50Perc)
                    start.linkTo(parent.start)
                    end.linkTo(guidelineFromStart30Perc)
                }
                .padding(start = dp_8)
        )

        Text(
            text = stringResource(id = R.string.movie_name),
            modifier = Modifier.constrainAs(movieName) {
                top.linkTo(backgroundImage.bottom)
                start.linkTo(posterImage.end)
            }, color = Color.Black,
        fontSize = sp_32)

        Text(
            text = stringResource(id = R.string.rating_value),
            modifier = Modifier.constrainAs(ratingValue) {
                top.linkTo(movieName.bottom)
                start.linkTo(guidelineFromStart30Perc)
            }.wrapContentWidth().wrapContentHeight())

        Text(
            text = stringResource(id = R.string.rating_lable),
            modifier = Modifier.constrainAs(ratingLabel) {
                top.linkTo(ratingValue.bottom)
                start.linkTo(guidelineFromStart30Perc)
            })

        Text(
            text = stringResource(id = R.string.grade_now),
            modifier = Modifier.constrainAs(movieName) {
                top.linkTo(guidelineFromTop30Perc)
                start.linkTo(guidelineFromStart30Perc)
            })
    }
}