package com.example.jetpack_compose_all_in_one.features.profile_update

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.dp_24
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30
import com.example.jetpack_compose_all_in_one.ui.theme.dp_40
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.dp_60
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.dp_80
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_20
import com.example.jetpack_compose_all_in_one.ui.theme.sp_25
import com.example.jetpack_compose_all_in_one.ui.theme.sp_28
import java.time.format.DateTimeFormatter

@Composable
fun InflateProfileUI() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val profileData = viewModel.profileData.observeAsState()

    viewModel.getProfileData()

    var displayMode by remember { mutableStateOf("UserInfoUI") }

    LaunchedEffect(key1 = false) {
        displayMode = if (profileData.value?.name?.isNotEmpty() == true) {
            "UserInfoUI"
        } else {
            "CreateProfileUI"
        }
    }

    when (displayMode) {
        "UserInfoUI" -> UserInfoUI(viewModel = viewModel) { displayMode = "CreateProfileUI" }
        "CreateProfileUI" -> CreateProfileUI(
            viewModel = viewModel,
        ) {
            displayMode = "UserInfoUI"
        }
    }
}

@Composable
fun CreateProfileUI(
    viewModel: ProfileViewModel,
    onUpdateButtonClicked: () -> Unit
) {
    ScrollableColumn {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp_10)
                .background(Color.White)
        ) {
            val (image, nameText, name, emailText, email, aboutText, about, button, address) = createRefs()
            val isAddedData = !viewModel.profileData.value?.name.isNullOrEmpty()
            val focusManager = LocalFocusManager.current

            val imageUri = rememberSaveable { mutableStateOf("") }
            val painter = rememberAsyncImagePainter(
                imageUri.value.ifEmpty {
                    R.drawable.actor1
                }
            )

            val launcher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                    uri?.let {
                        imageUri.value = it.toString()
                    }
                }
            Column(
                modifier = Modifier
                    .padding(dp_8)
                    .fillMaxWidth()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(top = dp_60)
                        .size(dp_100)

                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.nameText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom)
                    }
            )

            var username by remember {
                mutableStateOf(TextFieldValue(viewModel.profileData.value?.name ?: ""))
            }

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
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Text(
                text = stringResource(id = R.string.emailText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(emailText) {
                        top.linkTo(name.bottom)

                    }
            )


            var userEmail by remember {
                mutableStateOf(TextFieldValue(viewModel.profileData.value?.email ?: ""))
            }

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
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Text(
                text = stringResource(id = R.string.aboutText),
                modifier = Modifier
                    .padding(start = dp_30, top = dp_24, bottom = dp_5)
                    .constrainAs(aboutText) {
                        top.linkTo(address.bottom)
                    }
            )

            var aboutUser by remember {
                mutableStateOf(TextFieldValue(viewModel.profileData.value?.about ?: ""))
            }

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
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        defaultKeyboardAction(imeAction = ImeAction.Done)
                    }
                )
            )

            val street = rememberSaveable { mutableStateOf("") }
            val city = rememberSaveable { mutableStateOf("") }
            val state = rememberSaveable { mutableStateOf("") }
            val postalCode = rememberSaveable { mutableStateOf("") }

            AddressScreen(
                modifier = Modifier
                    .padding(dp_10)
                    .constrainAs(address)
                    {
                        top.linkTo(email.bottom)
                        start.linkTo(parent.start)
                    },
                street.value,
                city.value,
                state.value,
                postalCode.value,
                onStreetChange = { newStreet -> street.value = newStreet },
                onCityChange = { newCity -> city.value = newCity },
                onStateChange = { newState -> state.value = newState },
                onPostalCodeChange = { newPostalCode -> postalCode.value = newPostalCode },
            )

            Button(
                onClick = {
                    val location = Address(
                        id = 0,
                        street = street.value,
                        city = city.value,
                        state = state.value,
                        postalCode = postalCode.value
                    )
                    val data = Profile(
                        userId = if (isAddedData) viewModel.profileData.value!!.userId else 0,
                        name = username.text,
                        email = userEmail.text,
                        about = aboutUser.text,
                        imageUri = imageUri.value,
                        age = 0,
                        address = location
                    )

                    if (isAddedData) {
                        viewModel.updateProfile(data)
                        viewModel.getProfileData()
                    } else {
                        viewModel.addProfile(data)
                        viewModel.getProfileData()
                    }
                    onUpdateButtonClicked.invoke()
                },
                modifier = Modifier
                    .padding(start = dp_30, top = dp_30, end = dp_30)
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


@Composable
fun UserInfoUI(viewModel: ProfileViewModel, onUpdateButtonClicked: () -> Unit) {

    //val painter: Painter = rememberAsyncImagePainter(Uri.parse(viewModel.profileData.value?.imageUri))
    val painter: Painter =
        rememberAsyncImagePainter(Uri.parse(viewModel.profileData.value?.imageUri ?: "default_uri"))
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
            about,
            time) = createRefs()

        viewModel.getProfileData()
        viewModel.profileData.value?.imageUri?.let {
            Image(
                painter = painter,
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
        }


        viewModel.profileData.value?.let {
            Text(
                text = it.name,
                modifier = Modifier
                    .padding(top = dp_30, bottom = dp_5)
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = sp_25
            )
        }

        viewModel.profileData.value?.let {
            Text(
                text = it.email,
                modifier = Modifier
                    .padding(bottom = dp_5)
                    .constrainAs(emailText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                fontSize = sp_16
            )
        }

        Button(
            onClick = {
                onUpdateButtonClicked.invoke()
            },
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

        Text(
            text = stringResource(id = R.string.ratingText),
            modifier = Modifier
                .padding(start = dp_40, top = dp_5)
                .constrainAs(ratingText) {
                    top.linkTo(ratingNum.bottom)
                    start.linkTo(parent.start)

                },
            fontSize = sp_20
        )

        Text(
            text = stringResource(id = R.string.followingNum),
            modifier = Modifier
                .padding(top = dp_30)
                .constrainAs(followingNum) {
                    top.linkTo(button.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_25
        )

        Text(
            text = stringResource(id = R.string.followingText),
            modifier = Modifier
                .padding(top = dp_5)
                .constrainAs(followingText) {
                    top.linkTo(ratingNum.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_20
        )

        Text(
            text = stringResource(id = R.string.follerNum),
            modifier = Modifier
                .padding(top = dp_30, end = dp_50)
                .constrainAs(followerNum) {
                    top.linkTo(button.bottom)
                    end.linkTo(parent.end)
                },
            fontSize = sp_25
        )

        Text(
            text = stringResource(id = R.string.follerText),
            modifier = Modifier
                .padding(top = dp_5, end = dp_40)
                .constrainAs(followerText) {
                    top.linkTo(ratingNum.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(followerNum.start)
                },
            fontSize = sp_20
        )

        Text(
            text = stringResource(id = R.string.aboutText),
            modifier = Modifier
                .padding(start = dp_40, top = dp_30)
                .constrainAs(aboutText) {
                    top.linkTo(ratingText.bottom)
                    start.linkTo(parent.start)

                },
            fontSize = sp_28,
            fontWeight = FontWeight.Bold
        )

        viewModel.profileData.value?.let {
            Text(
                text = it.date.format(DateTimeFormatter.ofPattern("E, MMM dd h:mm a")),
                modifier = Modifier
                    .padding(start = dp_40, top = dp_30)
                    .constrainAs(time) {
                        top.linkTo(ratingText.bottom)
                        start.linkTo(aboutText.end)
                        bottom.linkTo(aboutText.bottom)
                    },
                fontSize = sp_16,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
        }

        viewModel.profileData.value?.let {
            Text(
                text = it.about,
                modifier = Modifier
                    .padding(start = dp_40, top = dp_10)
                    .constrainAs(about) {
                        top.linkTo(aboutText.bottom)
                        start.linkTo(parent.start)
                    },
                fontSize = sp_20,
            )
        }
    }
}

@Composable
fun AddressScreen(
    modifier: Modifier,
    street: String,
    city: String,
    state: String,
    postalCode: String,
    onStreetChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onStateChange: (String) -> Unit,
    onPostalCodeChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Address",
            modifier = Modifier
                .padding(start = dp_20, top = dp_24, bottom = dp_5)
        )

        TextField(
            value = street,
            onValueChange = onStreetChange,
            label = { Text("Street") },
            modifier = Modifier
                .padding(start = dp_20, bottom = dp_10, end = dp_20)
                .fillMaxWidth()
                .clip(RoundedCornerShape(dp_10)),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = city,
            onValueChange = onCityChange,
            label = { Text("City") },
            modifier = Modifier
                .padding(start = dp_20, top = dp_10, bottom = dp_10, end = dp_20)
                .fillMaxWidth()
                .clip(RoundedCornerShape(dp_10)),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state,
            onValueChange = onStateChange,
            label = { Text("State") },
            modifier = Modifier
                .padding(start = dp_20, top = dp_10, bottom = dp_10, end = dp_20)
                .fillMaxWidth()
                .clip(RoundedCornerShape(dp_10)),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = postalCode,
            onValueChange = onPostalCodeChange,
            label = { Text("Postal Code") },
            modifier = Modifier
                .padding(start = dp_20, top = dp_10, bottom = dp_10, end = dp_20)
                .fillMaxWidth()
                .clip(RoundedCornerShape(dp_10)),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}


//@Composable
//fun LoadImageFromUri(uri: Uri) {
//    val context = LocalContext.current
//
//    val imageBitmap: ImageBitmap? = loadImageResource(uri = uri) {
//        scale(IntSize(300, 300))
//    }.value?.asImageBitmap()
//
//    imageBitmap?.let {
//        Image(
//            bitmap = it,
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//    }
//}

