package com.example.jetpack_compose_all_in_one.lessons.lesson_7


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
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
    ElevatedCard(Modifier.fillMaxSize().padding(32.dp)) {
        ConstraintLayout(Modifier.fillMaxSize()){
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

            Box( Modifier.background(Color.Black)
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

            Box( Modifier.background(Color.Black)
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

            Box( Modifier.background(Color.Black)
                .height(30.dp)
                .constrainAs(smile4) {
                    bottom.linkTo(bottomGuide)
                    centerHorizontallyTo(parent)
                    width = Dimension.percent(0.2f)
                }
            )

            Box( Modifier.background(Color.Black)
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

            Box( Modifier.background(Color.Black)
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

//Alex
@Composable
fun LoginScreen() {

}

//Honjia
@Composable
fun PlayWithBox() {

}

@Preview(showSystemUi = true)
@Composable
private fun CardWithDetailsInsidePreview() {
    CardWithDetailsInside()
}