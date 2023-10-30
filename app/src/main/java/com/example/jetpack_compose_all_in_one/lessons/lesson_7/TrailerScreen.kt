package com.example.jetpack_compose_all_in_one.lessons.lesson_7

import android.net.Uri
import android.widget.VideoView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpack_compose_all_in_one.R


@Composable
fun TrailerScreen(){
    VideoPlayerWithImageOverlay(
        videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        imageRes = R.drawable.avatar)
}

@Composable
fun VideoPlayerWithImageOverlay(videoUrl: String, @DrawableRes imageRes: Int) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (videoPlayer, poster, title, rating, watchNow, watchList, synopsisTitle, synopsisText, castTitle, castList) = createRefs()
        val horizontalGuide = createGuidelineFromTop(0.3f)
        AndroidView(
            modifier = Modifier
                .constrainAs(videoPlayer) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                  bottom.linkTo(horizontalGuide)
                },
            factory = { context ->
                VideoView(context).apply {
                    setVideoURI(Uri.parse(videoUrl))
                    start()
                }
            }
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(poster) {
                    top.linkTo(horizontalGuide)
                    start.linkTo(parent.start, 50.dp)
                }
                .offset(y = (-50).dp)
                .width(100.dp)
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .constrainAs(title){
                    start.linkTo(poster.end, 30.dp)
                    top.linkTo(videoPlayer.bottom, 30.dp)
                },
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Avatar")

        RatingBar(
            modifier = Modifier
                .constrainAs(rating){
                    start.linkTo(poster.end, 30.dp)
                    top.linkTo(title.bottom, 10.dp)
                },
            rating = 4
        )

        Button(
            modifier = Modifier
                .constrainAs(watchNow) {
                    start.linkTo(parent.start, 30.dp)
                    top.linkTo(poster.bottom)
                },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
            onClick = { /*TODO*/ })
        {
            Text(text = "Watch Now")
        }

        Button(
            modifier = Modifier
                .constrainAs(watchList) {
                    start.linkTo(watchNow.end, 30.dp)
                    top.linkTo(poster.bottom)
                },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = { /*TODO*/ })
        {
            Text(text = "Add to Watch List")
        }

        Text(
            modifier = Modifier
                .constrainAs(synopsisTitle){
                    start.linkTo(parent.start, 30.dp)
                    top.linkTo(watchNow.bottom, 10.dp)
                },
            color = Color.Red,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Synopsis")

        Text(
            modifier = Modifier
                .constrainAs(synopsisText){
                    start.linkTo(parent.start, 30.dp)
                    top.linkTo(synopsisTitle.bottom, 10.dp)
                    end.linkTo(parent.end, 20.dp)
                    width = Dimension.fillToConstraints
                },
            fontSize = 15.sp,
            text = "Avatar is an American media franchise created by James Cameron, which consists of a planned series of epic science fiction films produced by Lightstorm Entertainment and distributed by 20th Century Studios.")

        Text(
            modifier = Modifier
                .constrainAs(castTitle){
                    start.linkTo(parent.start, 30.dp)
                    top.linkTo(synopsisText.bottom, 10.dp)
                },
            color = Color.Red,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            text = "Cast")

        Cast(
            modifier = Modifier
                .constrainAs(castList) {
                    start.linkTo(parent.start, 30.dp)
                    top.linkTo(castTitle.bottom, 10.dp)
                }
        )
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 5
) {
    Row(modifier = modifier) {
        for (i in 1..rating) {
            val starModifier = Modifier
                .padding(end = 3.dp)
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating $i",
                modifier = starModifier,
                tint = Color.Yellow
            )
        }
    }
}

@Composable
fun Cast(
    modifier: Modifier = Modifier,
    nums: Int = 4
) {
    Row(modifier = modifier) {
        for (i in 1..nums) {
            Column {
                Image(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.actor1),
                    contentDescription = null)
                Text(text = "Actor $i")
            }
            Spacer(Modifier.size(5.dp))
        }
    }
}

