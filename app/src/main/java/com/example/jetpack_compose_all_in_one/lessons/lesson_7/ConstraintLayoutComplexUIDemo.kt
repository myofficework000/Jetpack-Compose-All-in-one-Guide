package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.TextButton
import com.example.jetpack_compose_all_in_one.ui.theme.AqiGradient
import com.example.jetpack_compose_all_in_one.ui.theme.Blue20
import com.example.jetpack_compose_all_in_one.ui.theme.OceanA100
import com.example.jetpack_compose_all_in_one.ui.theme.OrangeA100
import com.example.jetpack_compose_all_in_one.ui.theme.PAGER_BACKGROUND
import com.example.jetpack_compose_all_in_one.ui.theme.VioletA100
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

@Preview
@Composable
fun GetMusicUI() {
//Alex
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        PAGER_BACKGROUND,
                        Blue20,
                        VioletA100,
                        OrangeA100
                    )
                )
            )

    ) {
        val (collapseBtn, profileIcon, songTitle, songImg) = createRefs()
        val (favIcon, moreIcon) = createRefs()
        val (progressbar, startTime, endTime) = createRefs()
        val (shuffleBtn, prevBtn, pauseBtn, nextBtn, repeatBtn) = createRefs()
        val (speakerBtn, lyricsBtn, shareBtn) = createRefs()

        val topGuideLine10Percent = createGuidelineFromTop(0.1f)

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(collapseBtn){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(songTitle.top)
                }
        ) {
           Icon(imageVector = Icons.Default.ExpandMore, contentDescription = "Expand" )
        }

        Text(
            text = "ROCKSTAR.(feat roddy ricch)",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(songTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(collapseBtn.bottom)
                    top.linkTo(topGuideLine10Percent)
                }
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(profileIcon) {
                    top.linkTo(collapseBtn.bottom)
                    top.linkTo(topGuideLine10Percent)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                ,
                contentDescription = "Profile"
            )
            Icon(
                imageVector =  Icons.Default.ChevronRight,
                contentDescription = "Profile",
                tint = Color.LightGray
            )
        }

        createHorizontalChain(songTitle, profileIcon,
        chainStyle = ChainStyle.SpreadInside)

        Image(
            painter = painterResource(id = R.drawable.rockstar),
            contentDescription = "",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .constrainAs(songImg) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(pauseBtn.top)
                    end.linkTo(parent.end)
                }
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(favIcon) {
                    start.linkTo(parent.start)
                    bottom.linkTo(progressbar.top)
                    end.linkTo(moreIcon.start, 5.dp)
                }
        ) {
            Icon(
                imageVector =  Icons.Default.Favorite,
                contentDescription = "Shuffle",
                tint = Color.White
            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(moreIcon) {
                    start.linkTo(favIcon.end, 5.dp)
                    bottom.linkTo(progressbar.top)
                    end.linkTo(parent.end)
                },
        ) {
            Icon(
                imageVector =  Icons.Default.MoreHoriz,
                contentDescription = "More",
                tint = Color.White
            )
        }

        createHorizontalChain(favIcon,moreIcon,
        chainStyle = ChainStyle.Packed(0.95f))


        LinearProgressIndicator(
            progress = 0.4f,
            modifier = Modifier
                .constrainAs(progressbar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(startTime.top)
                    bottom.linkTo(endTime.top)
                }
                .padding(10.dp)
                .fillMaxWidth(0.9f)
                .height(8.dp),
            backgroundColor = Color.LightGray,
            strokeCap = StrokeCap.Round,
            color = OceanA100 //progress color
        )

        Text(
            text = "1:28",
            modifier = Modifier
                .constrainAs(startTime){
                    start.linkTo(progressbar.start)
                    top.linkTo(progressbar.top)
                    bottom.linkTo(shuffleBtn.top)
                }
        )

        Text(
            text = "-0:12",
            modifier = Modifier
                .constrainAs(endTime){
                    end.linkTo(progressbar.end)
                    top.linkTo(progressbar.top)
                    bottom.linkTo(repeatBtn.top)
                }
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(shuffleBtn){
                    start.linkTo(parent.start)
                    bottom.linkTo(speakerBtn.top)
                    end.linkTo(prevBtn.start)
                }
        ) {
            Icon(imageVector =  Icons.Default.Shuffle, contentDescription = "Shuffle")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(prevBtn){
                    start.linkTo(shuffleBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector =  Icons.Default.SkipPrevious , contentDescription = "Previous" )


        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(pauseBtn){
                    start.linkTo(prevBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector =  Icons.Default.Pause, contentDescription = "Pause")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(nextBtn){
                    start.linkTo(pauseBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector =  Icons.Default.SkipNext, contentDescription = "Next")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(repeatBtn){
                    start.linkTo(nextBtn.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(shareBtn.top)

                }
        ) {
            Icon(imageVector =  Icons.Default.Repeat, contentDescription = "Repeat")
        }

        createHorizontalChain(shuffleBtn, prevBtn, pauseBtn, nextBtn, repeatBtn,
            chainStyle = ChainStyle.SpreadInside)


        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(speakerBtn){
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painter = painterResource( id = R.drawable.baseline_speaker_24) ,
                contentDescription = "Speaker" )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(lyricsBtn){
                    bottom.linkTo(parent.bottom)
                }
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource( id = R.drawable.baseline_keyboard_arrow_up_24) ,
                    contentDescription = "Lyrics" )
                Text(text = "Lyrics")
            }
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(shareBtn){
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painter = painterResource( id = R.drawable.baseline_ios_share_24) ,
                contentDescription = "Share" )
        }
        createHorizontalChain(speakerBtn,lyricsBtn, shareBtn,
        chainStyle = ChainStyle.SpreadInside)
    }
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