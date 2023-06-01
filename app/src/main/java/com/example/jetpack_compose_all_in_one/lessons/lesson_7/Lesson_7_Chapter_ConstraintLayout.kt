package com.example.jetpack_compose_all_in_one.lessons.lesson_7


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.google.maps.android.compose.Circle
import com.google.mlkit.vision.common.Triangle

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

}

//Alex
@Composable
fun LoginScreen() {

}

//Hongjia
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
            painter = painterResource(id = R.drawable.yasuo),
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
                fontSize = 20.sp,
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
                fontSize = 20.sp,
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
                fontSize = 20.sp,
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
