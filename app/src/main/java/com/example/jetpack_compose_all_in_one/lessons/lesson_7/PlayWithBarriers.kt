package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_24
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32


@Preview
@Composable
fun PlayWithBarriers() {
    val userName = "Abhishek"
    val greetingMessageWithUser = "Welcome! \n$userName"

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val (greetingMessage, askingMessage, profile, doctorImage) = createRefs()
        val greetingsBarrier = createEndBarrier(
            greetingMessage, askingMessage
        )

        Image(
            painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.not_required_for_accessiblity),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(profile) {
                    top.linkTo(parent.top, margin = dp_8)
                    start.linkTo(parent.start, margin = dp_8)
                }
                .size(dp_50)
                .clip(CircleShape)
        )

        Text(
            text = greetingMessageWithUser,
            fontSize = sp_32,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.constrainAs(greetingMessage) {
                top.linkTo(profile.bottom, margin = dp_24)
                start.linkTo(parent.start, margin = dp_8)
            }
        )

        Image(
            painterResource(id = R.drawable.doctor_image_bg),
            contentDescription = stringResource(id = R.string.not_required_for_accessiblity),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(doctorImage) {
                    top.linkTo(profile.bottom, margin = dp_8)
                    start.linkTo(greetingsBarrier)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .clip(CircleShape)
                .fillMaxSize()
        )
    }
}

@Composable
fun PlayWithBarrierExample2() {
    //Joshua
}