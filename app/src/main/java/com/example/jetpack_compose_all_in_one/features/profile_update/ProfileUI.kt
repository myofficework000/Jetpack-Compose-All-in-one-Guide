package com.example.jetpack_compose_all_in_one.features.profile_update

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.dp_24
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.dp_80
import com.example.jetpack_compose_all_in_one.ui.theme.sp_14
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_25
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32

@Composable
fun CreateProfileUI() {
    ScrollableColumn {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp_10)
                .background(Color.White)
        ) {
            val viewModel: ProfileViewModel = hiltViewModel()
            val (image, nameText, name, emailText, email, aboutText, about, button) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.actor1),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = dp_80)
                    .clip(CircleShape)
                    .size(dp_100)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = stringResource(id = R.string.nameText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom)
                    }
            )

            var username by remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.nameText))
                },
                modifier = Modifier
                    .padding(start = dp_30, end = dp_30, bottom = dp_10)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dp_10))
                    .constrainAs(name) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = stringResource(id = R.string.emailText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(emailText) {
                        top.linkTo(name.bottom)

                    }
            )

            var userEmail by remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.emailText))
                },
                modifier = Modifier
                    .padding(start = dp_30, end = dp_30, bottom = dp_10)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dp_10))
                    .constrainAs(email) {
                        top.linkTo(emailText.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = stringResource(id = R.string.aboutText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(aboutText) {
                        top.linkTo(email.bottom)
                    }
            )

            var aboutUser by remember { mutableStateOf(TextFieldValue()) }

//        val screenWidth = with(LocalDensity.current) {
//            LocalContext.current.resources.displayMetrics.widthPixels.dp
//        }
//        val oneThirdHeight: Dp = screenWidth / 3
//
//        val aspectRatioModifier = Modifier
//            .layoutId(aboutUser)
//            .aspectRatio(3f/1f) // Set the aspect ratio to 1:3 (height:width)

            TextField(
                value = aboutUser,
                onValueChange = { aboutUser = it },
                placeholder = {
                    Text(text = stringResource(id = R.string.aboutText))
                },
                modifier = Modifier
                    .padding(start = dp_30, bottom = dp_10, end = dp_30)
                    .clip(RoundedCornerShape(dp_10))
                    .aspectRatio(2f / 1f)
                    .constrainAs(about) {
                        top.linkTo(aboutText.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Button(
                onClick = {
                    val data = Profile(
                        userId = 0,
                        name = username.text,
                        email = userEmail.text,
                        about = aboutUser.text
                    )
                    viewModel.addProfile(data)
                },
                modifier = Modifier
                    .padding(start = dp_30, top = dp_50, end = dp_30)
                    .fillMaxWidth()
                    .constrainAs(button) {
                        top.linkTo(about.bottom)
                        start.linkTo(parent.start)
                    },
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.elevation(dp_10),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}


@Preview
@Composable
fun UserInfoUI() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(dp_10)
            .background(Color.White)
    ) {
        val (image,
            nameText,
            emailText,
            button,
            ratingNum,
            ratingText,
            followingNum,
            followingText,
            followerNum,
            followerText,
            aboutText,
            about) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.actor1),
            contentDescription = null,
            modifier = Modifier
                .padding(top = dp_80)
                .clip(CircleShape)
                .size(dp_100)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.nameText),
            modifier = Modifier
                .padding(top = dp_30, bottom = dp_5)
                .constrainAs(nameText) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_25
        )

        Text(
            text = stringResource(id = R.string.emailText),
            modifier = Modifier
                .padding(bottom = dp_5)
                .constrainAs(emailText) {
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_16
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = dp_30, top = dp_20, end = dp_30)
                .constrainAs(button) {
                    top.linkTo(emailText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(dp_50),
            colors = ButtonDefaults.buttonColors(Color.Cyan)
        ) {
            Text(text = stringResource(id = R.string.updateProfile))
        }

        Text(
            text = stringResource(id = R.string.ratingNum),
            modifier = Modifier
                .padding(start = dp_50, top = dp_30)
                .constrainAs(ratingNum) {
                    top.linkTo(button.bottom)
                    start.linkTo(parent.start)
                },
            fontSize = sp_25
        )
    }
}