package com.example.jetpack_compose_all_in_one.lessons.lesson_7


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.theme.LightBlue
import com.example.jetpack_compose_all_in_one.ui.theme.VioletA100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
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
    ElevatedCard(
        Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val leftGuide = createGuidelineFromStart(0.2f)
            val rightGuide = createGuidelineFromEnd(0.2f)
            val topGuide = createGuidelineFromTop(0.3f)
            val bottomGuide = createGuidelineFromBottom(0.3f)
            val (leftEye, rightEye, smile2, smile3, smile4, smile5, smile6) =
                createRefs()

            SimpleIconButton(
                R.drawable.baseline_play_circle_outline_24,
                Modifier.constrainAs(leftEye) {
                    top.linkTo(topGuide)
                    start.linkTo(leftGuide)
                },
                Modifier.size(48.dp),
                tint = Color.Blue
            ) {}

            SimpleIconButton(
                R.drawable.baseline_play_circle_outline_24,
                Modifier.constrainAs(rightEye) {
                    top.linkTo(topGuide)
                    end.linkTo(rightGuide)
                    rotationZ = 180f
                },
                Modifier.size(48.dp),
                tint = Color.Red
            ) {}

            Box(
                Modifier
                    .background(Color.Black)
                    .height(30.dp)
                    .constrainAs(smile2) {
                        top.linkTo(smile3.top)
                        end.linkTo(smile3.start)
                        width = Dimension.percent(0.2f)
                        rotationZ = 40f
                        translationX = 25.dp
                        translationY = (-40).dp
                    }
            )

            Box(
                Modifier
                    .background(Color.Black)
                    .height(30.dp)
                    .constrainAs(smile3) {
                        top.linkTo(smile4.top)
                        end.linkTo(smile4.start)
                        width = Dimension.percent(0.2f)
                        rotationZ = 20f
                        translationX = 10.dp
                        translationY = (-10).dp
                    }
            )

            Box(
                Modifier
                    .background(Color.Black)
                    .height(30.dp)
                    .constrainAs(smile4) {
                        bottom.linkTo(bottomGuide)
                        centerHorizontallyTo(parent)
                        width = Dimension.percent(0.2f)
                    }
            )

            Box(
                Modifier
                    .background(Color.Black)
                    .height(30.dp)
                    .constrainAs(smile5) {
                        top.linkTo(smile4.top)
                        start.linkTo(smile4.end)
                        width = Dimension.percent(0.2f)
                        rotationZ = -20f
                        translationX = -10.dp
                        translationY = (-10).dp
                    }
            )

            Box(
                Modifier
                    .background(Color.Black)
                    .height(30.dp)
                    .constrainAs(smile6) {
                        top.linkTo(smile5.top)
                        start.linkTo(smile5.end)
                        width = Dimension.percent(0.2f)
                        rotationZ = -40f
                        translationX = (-25).dp
                        translationY = (-40).dp
                    }
            )
        }
    }
}

//Luan
@Composable
fun RegistrationScreen() {

}

@Preview
@Composable
fun LoginScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val (title, emailTf, passTf, enterBtn, subText, facebookBtn) = createRefs()
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Text(
            text = "SIGN IN",
            modifier = Modifier
                .constrainAs(title)
                {
                    top.linkTo(parent.top, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_32,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
        )

        TextField(
            value = email.value,
            modifier = Modifier
                .constrainAs(emailTf)
                {
                    top.linkTo(title.bottom, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = { email.value = it },
            label = { Text(text = "email address") },
            placeholder = { Text(text = "Type your email") },
            shape = RoundedCornerShape(20.dp),
        )

        TextField(
            value = password.value,
            modifier = Modifier
                .constrainAs(passTf)
                {
                    top.linkTo(emailTf.bottom, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = { password.value = it },
            label = { Text(text = "password") },
            shape = RoundedCornerShape(20.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(enterBtn)
                {
                    top.linkTo(passTf.bottom, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.textButtonColors(
                VioletA100
            )
        ) {
            Text(
                text = "ENTER",
                fontSize = sp_16,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Text(
            text = "Or Sign In With",
            modifier = Modifier
                .constrainAs(subText) {
                    top.linkTo(enterBtn.bottom, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            fontSize = sp_16,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,

            )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(facebookBtn) {
                top.linkTo(subText.bottom, margin = dp_20)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = dp_50)
            }
        ) {
            Text(
                text = "f",
                fontSize = sp_32,
                fontWeight = FontWeight.SemiBold,
                color = LightBlue
            )
            Spacer(modifier = Modifier.height(dp_5))
            Text(
                text = "Facebook",
                fontSize = sp_16,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Composable
fun PlayWithBox() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .padding(10.dp)
    ) {
        val (image, box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11, box12) = createRefs()
        Image(
            modifier = Modifier.constrainAs(image) {},
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "champion"
        )

        Box(
            modifier = Modifier
                .constrainAs(box1) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(10.dp)
                .background(Color.Magenta)
                .size(300.dp)
        ) {
            Text(
                text = "1",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box2) {
                    top.linkTo(box1.top)
                    bottom.linkTo(box1.bottom)
                    start.linkTo(box1.start)
                    end.linkTo(box1.end)
                }
                .padding(10.dp)
                .background(Color.Cyan)
                .size(250.dp)
        )
        {
            Text(
                text = "2",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box3) {
                    top.linkTo(box2.top)
                    bottom.linkTo(box2.bottom)
                    start.linkTo(box2.start)
                    end.linkTo(box2.end)
                }
                .padding(10.dp)
                .background(Color.Red)
                .size(200.dp)
        ) {
            Text(
                text = "3",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box4) {
                    top.linkTo(box3.top)
                    bottom.linkTo(box3.bottom)
                    start.linkTo(box3.start)
                    end.linkTo(box3.end)
                }
                .padding(10.dp)
                .background(Color.Blue)
                .size(150.dp)
        ) {
            Text(
                text = "4",
                fontSize = sp_16,
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box5) {
                    top.linkTo(box4.top)
                    bottom.linkTo(box4.bottom)
                    start.linkTo(box4.start)
                    end.linkTo(box4.end)
                }
                .padding(10.dp)
                .background(Color.Green)
                .size(100.dp)
        )
        {
            Text(
                text = "5",
                fontSize = sp_16,
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box6) {
                    top.linkTo(box5.top)
                    bottom.linkTo(box5.bottom)
                    start.linkTo(box5.start)
                    end.linkTo(box5.end)
                }
                .padding(10.dp)
                .background(Color.Black)
                .size(50.dp)
        ) {
            Text(
                text = "6",
                fontSize = sp_16,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Yellow
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(box7) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .padding(20.dp)
                .clip(shape = CircleShape)
                .background(Color.LightGray)
                .size(150.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(box8) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(box7.start)
                    end.linkTo(box7.end)
                }
                .padding(20.dp)
                .clip(shape = CircleShape)
                .background(Color.Gray)
                .size(120.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(box9) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(box8.start)
                    end.linkTo(box8.end)
                }
                .padding(20.dp)
                .clip(shape = CircleShape)
                .background(Color.DarkGray)
                .size(90.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(box10) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(20.dp)
                .clip(shape = CutCornerShape(40.dp))
                .background(Color.Red)
                .size(150.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(box11) {
                    start.linkTo(box10.start)
                    end.linkTo(box10.end)
                    bottom.linkTo(box10.bottom)
                    top.linkTo(box10.top)
                }
                .padding(40.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.Cyan)
                .size(100.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(box12) {
                    start.linkTo(box11.start)
                    end.linkTo(box11.end)
                    top.linkTo(box11.top)
                    bottom.linkTo(box11.bottom)
                }
                .padding(20.dp)
                .clip(shape = RectangleShape)
                .background(Color.Blue)
                .size(50.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CardWithDetailsInsidePreview() {
    CardWithDetailsInside()
}