package com.example.jetpack_compose_all_in_one.lessons.lesson_7


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_24
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Preview
@Composable
fun Lesson_7_Chapter_ConstraintLayout() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 4,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.lesson_7_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> PlayWithBox()
                1 -> LoginScreen()
                2 -> RegistrationScreen()
                3 -> CardWithDetailsInside()
            }
        }
    }
}

@Composable
fun CardWithDetailsInside() {

}

//Luan
@Composable
fun RegistrationScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(dp_8)
        .background(Color.White)){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp_24)
        ) {
            val (card) = createRefs()
            
            Card(
                modifier = Modifier
                    .constrainAs(card){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
            }, elevation = CardDefaults.cardElevation(
                defaultElevation = dp_15
            ),
                shape = RoundedCornerShape(dp_15)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dp_15)
                ){
                    val (signUpText,usernameTextField, passwordTextField, emailTextField, registerButton) = createRefs()
                    Text(
                        modifier = Modifier
                        .constrainAs(signUpText) {
                            top.linkTo(parent.top, margin = dp_15)
                            start.linkTo(parent.start, margin = dp_15)
                            end.linkTo(parent.end, margin = dp_15)
                        }
                        .fillMaxWidth(),
                        text = "Sign Up",
                        textAlign = TextAlign.Center
                    )
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        modifier = Modifier
                            .constrainAs(usernameTextField) {
                                top.linkTo(signUpText.bottom, margin = dp_15)
                                start.linkTo(parent.start, margin = dp_15)
                                end.linkTo(parent.end, margin = dp_15)
                            }
                            .fillMaxWidth()
                    )

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier
                            .constrainAs(passwordTextField) {
                                top.linkTo(usernameTextField.bottom, margin = 16.dp)
                                start.linkTo(parent.start, margin = dp_15)
                                end.linkTo(parent.end, margin = dp_15)
                            }
                            .fillMaxWidth()
                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .constrainAs(emailTextField) {
                                top.linkTo(passwordTextField.bottom, margin = dp_15)
                                start.linkTo(parent.start, margin = dp_15)
                                end.linkTo(parent.end, margin = dp_15)
                            }
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = { /* Perform registration */ },
                        modifier = Modifier
                            .constrainAs(registerButton) {
                                top.linkTo(emailTextField.bottom, margin = dp_24)
                                start.linkTo(parent.start, margin = dp_15)
                                end.linkTo(parent.end, margin = dp_15)
                            }
                            .fillMaxWidth()
                    ) {
                        Text("Register")
                    }
                }
            }

        }
    }

}

//Alex
@Composable
fun LoginScreen() {

}

//Honjia
@Composable
fun PlayWithBox() {

}
