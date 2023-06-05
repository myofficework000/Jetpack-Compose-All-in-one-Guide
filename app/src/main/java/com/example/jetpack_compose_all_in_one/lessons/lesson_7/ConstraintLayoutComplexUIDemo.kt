package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn
import com.example.jetpack_compose_all_in_one.ui.components.TextButton
import com.example.jetpack_compose_all_in_one.ui.theme.AqiGradient
import com.example.jetpack_compose_all_in_one.ui.theme.Blue20
import com.example.jetpack_compose_all_in_one.ui.theme.OceanA100
import com.example.jetpack_compose_all_in_one.ui.theme.OrangeA100
import com.example.jetpack_compose_all_in_one.ui.theme.PAGER_BACKGROUND
import com.example.jetpack_compose_all_in_one.ui.theme.VioletA100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.dp_24
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30
import com.example.jetpack_compose_all_in_one.ui.theme.dp_60
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.dp_80
import com.example.jetpack_compose_all_in_one.ui.theme.sp_20
import kotlin.math.ceil
import kotlin.math.floor

@Preview
@Composable
fun MovieDetailScreenUI() {
    ScrollableColumn {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (profile, settings) = createRefs()
            val (backgroundImage, posterImage, movieName, photos, photoRow, actors, actorRow) = createRefs()
            val (ratingValue, ratingBar, ratingLabel, gradeLabel, storyLine, storyLineDescription, movieType1, movieType2) = createRefs()

            val guidelineFromTop30Perc = createGuidelineFromTop(0.30f)
            val guidelineFromTop25Perc = createGuidelineFromTop(0.25f)
            val guidelineFromTop50Perc = createGuidelineFromTop(0.50f)
            val guidelineFromStart40Perc = createGuidelineFromStart(0.40f)

            createHorizontalChain(profile, settings, chainStyle = ChainStyle.SpreadInside)

            Image(painterResource(id = R.drawable.dkbg),
                contentDescription = stringResource(id = R.string.movie_bg_image),
                modifier = Modifier
                    .constrainAs(backgroundImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(dp_10)
            )

            Image(
                painterResource(id = R.drawable.darkknight),
                contentDescription = stringResource(id = R.string.movie_poster_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(posterImage) {
                        top.linkTo(guidelineFromTop25Perc)
                        bottom.linkTo(storyLine.top)
                        start.linkTo(parent.start)
                        end.linkTo(guidelineFromStart40Perc)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .padding(start = dp_10)
            )

            Text(
                text = stringResource(id = R.string.movie_name),
                modifier = Modifier
                    .constrainAs(movieName) {
                        top.linkTo(guidelineFromTop30Perc)
                        start.linkTo(guidelineFromStart40Perc)
                    }
                    .padding(start = dp_10),
                color = Color.Black,
                fontSize = sp_20
            )

            Text(
                text = stringResource(id = R.string.story_line),
                modifier = Modifier
                    .constrainAs(storyLine) {
                        top.linkTo(guidelineFromTop50Perc)
                        start.linkTo(parent.start)
                    }
                    .padding(start = dp_24, top = dp_10),
                color = Color.Black
            )

            ExpandableText(
                text = stringResource(id = R.string.story_line_description),
                modifier = Modifier
                    .constrainAs(storyLineDescription) {
                        top.linkTo(storyLine.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = dp_24, top = dp_10, end = dp_24),
            )

            Text(
                text = stringResource(id = R.string.rating_value),
                modifier = Modifier
                    .constrainAs(ratingValue) {
                        top.linkTo(movieName.bottom)
                        start.linkTo(guidelineFromStart40Perc)
                    }
                    .padding(start = dp_10),
                color = Color.Red,
                fontSize = sp_20
            )

            Text(
                text = stringResource(id = R.string.rating_label),
                modifier = Modifier
                    .constrainAs(ratingLabel) {
                        top.linkTo(ratingValue.bottom)
                        start.linkTo(guidelineFromStart40Perc)
                    }
                    .padding(start = dp_10),
                color = Color.Gray
            )

            Text(
                text = stringResource(id = R.string.grade_now),
                modifier = Modifier
                    .constrainAs(gradeLabel) {
                        top.linkTo(ratingBar.bottom)
                        start.linkTo(ratingLabel.end)
                    }
                    .padding(start = dp_20),
                color = Color.Gray
            )

            RatingBar(
                rating = 4.0,
                modifier = Modifier
                    .constrainAs(ratingBar) {
                        start.linkTo(ratingValue.end)
                        bottom.linkTo(ratingValue.bottom)
                    }
                    .padding(start = dp_30)
            )

            Box(
                modifier = Modifier
                    .constrainAs(movieType1) {
                        top.linkTo(ratingLabel.bottom)
                        start.linkTo(ratingLabel.start)
                    }
                    .padding(dp_10)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                    .size(width = dp_60, height = dp_30)
            ) {
                Text(
                    text = stringResource(id = R.string.movie_type1),
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .constrainAs(movieType2) {
                        start.linkTo(movieType1.end)
                        top.linkTo(movieType1.top)
                        bottom.linkTo(movieType1.bottom)
                    }
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                    .size(width = dp_80, height = dp_30)
            ) {
                Text(
                    text = stringResource(id = R.string.movie_type2),
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.Black
                )
            }

            Text(
                text = stringResource(id = R.string.photos),
                modifier = Modifier
                    .constrainAs(photos) {
                        top.linkTo(storyLineDescription.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = dp_24, top = dp_30),
                color = Color.Black
            )

            LazyRow(
                modifier = Modifier
                    .constrainAs(photoRow) {
                        top.linkTo(photos.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(dp_10)
            ) {
                items(listOfPhotos) { PhotoItemUI(img = it) }
            }

            Text(
                text = stringResource(id = R.string.actors),
                modifier = Modifier
                    .constrainAs(actors) {
                        top.linkTo(photoRow.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = dp_24),
                color = Color.Black
            )

            LazyRow(
                modifier = Modifier
                    .constrainAs(actorRow) {
                        top.linkTo(actors.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                items(listOfActors) { ActorItemUI(actor = it) }
            }

        }
    }
}

data class Photo(val image: Int)
data class Actor(val image: Int, val name: String)

private val listOfPhotos = listOf(
    Photo(R.drawable.dk1),
    Photo(R.drawable.dk2),
    Photo(R.drawable.dk3),
    Photo(R.drawable.dk4),
    Photo(R.drawable.dk5),
    Photo(R.drawable.dk6),
)

private val listOfActors = listOf(
    Actor(R.drawable.actor1, "Christian Bale"),
    Actor(R.drawable.actor2, "Heath Ledger"),
    Actor(R.drawable.actor3, "Aaron Eckhart"),
    Actor(R.drawable.actor4, "Michael Caine"),
    Actor(R.drawable.actor5, "Maggie Gyllenhaal"),
    Actor(R.drawable.actor6, "Gary Oldman")
)

@Composable
fun PhotoItemUI(img: Photo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dp_8)
    ) {
        Image(painterResource(img.image), contentDescription = "Movie Image")
    }
}

@Composable
fun ActorItemUI(actor: Actor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dp_8)
    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            val (actorImage, actorName) = createRefs()
            Image(
                painterResource(actor.image),
                contentDescription = "Actor Image",
                modifier = Modifier
                    .padding(dp_8)
                    .clip(CircleShape)
                    .constrainAs(actorImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = actor.name,
                modifier = Modifier
                    .padding(dp_8)
                    .constrainAs(actorName) {
                        top.linkTo(actorImage.bottom)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

const val DEFAULT_MINIMUM_TEXT_LINE = 3

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "... Show More",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W900, color = Color.Red),
    showLessText: String = " Show Less",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier
        .clickable(clickable) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign
        )
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Red,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun GetAPIWeatherUI(aqi: Int) {
    val aqiState = remember {
        derivedStateOf {
            when (aqi) {
                1 -> "Good"
                2 -> "Fair"
                3 -> "Ok"
                4 -> "Unhealthy"
                5 -> "BRO GET INSIDE"
                else -> ""
            }
        }
    }

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
                        .background(AqiGradient)
                ) {}
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
                .constrainAs(collapseBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(songTitle.top)
                }
        ) {
            Icon(imageVector = Icons.Default.ExpandMore, contentDescription = "Expand")
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
                    .clip(CircleShape),
                contentDescription = "Profile"
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Profile",
                tint = Color.LightGray
            )
        }

        createHorizontalChain(
            songTitle, profileIcon,
            chainStyle = ChainStyle.SpreadInside
        )

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
                imageVector = Icons.Default.Favorite,
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
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "More",
                tint = Color.White
            )
        }

        createHorizontalChain(
            favIcon, moreIcon,
            chainStyle = ChainStyle.Packed(0.95f)
        )


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
                .constrainAs(startTime) {
                    start.linkTo(progressbar.start)
                    top.linkTo(progressbar.top)
                    bottom.linkTo(shuffleBtn.top)
                }
        )

        Text(
            text = "-0:12",
            modifier = Modifier
                .constrainAs(endTime) {
                    end.linkTo(progressbar.end)
                    top.linkTo(progressbar.top)
                    bottom.linkTo(repeatBtn.top)
                }
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(shuffleBtn) {
                    start.linkTo(parent.start)
                    bottom.linkTo(speakerBtn.top)
                    end.linkTo(prevBtn.start)
                }
        ) {
            Icon(imageVector = Icons.Default.Shuffle, contentDescription = "Shuffle")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(prevBtn) {
                    start.linkTo(shuffleBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector = Icons.Default.SkipPrevious, contentDescription = "Previous")


        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(pauseBtn) {
                    start.linkTo(prevBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector = Icons.Default.Pause, contentDescription = "Pause")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(nextBtn) {
                    start.linkTo(pauseBtn.end)
                    bottom.linkTo(speakerBtn.top)
                }
        ) {
            Icon(imageVector = Icons.Default.SkipNext, contentDescription = "Next")
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(repeatBtn) {
                    start.linkTo(nextBtn.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(shareBtn.top)

                }
        ) {
            Icon(imageVector = Icons.Default.Repeat, contentDescription = "Repeat")
        }

        createHorizontalChain(
            shuffleBtn, prevBtn, pauseBtn, nextBtn, repeatBtn,
            chainStyle = ChainStyle.SpreadInside
        )


        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(speakerBtn) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_speaker_24),
                contentDescription = "Speaker"
            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(lyricsBtn) {
                    bottom.linkTo(parent.bottom)
                }
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                    contentDescription = "Lyrics"
                )
                Text(text = "Lyrics")
            }
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(shareBtn) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_ios_share_24),
                contentDescription = "Share"
            )
        }
        createHorizontalChain(
            speakerBtn, lyricsBtn, shareBtn,
            chainStyle = ChainStyle.SpreadInside
        )
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