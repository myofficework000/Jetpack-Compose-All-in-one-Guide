package com.example.jetpack_compose_all_in_one.lessons.lesson_22

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * A composable function that creates a view for playing videos using ExoPlayer.
 */
@Composable
fun ExoPlayerView() {
    // URL of the video to be played
    val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a media item from the video URL
    val mediaSource = remember(videoUrl) {
        MediaItem.fromUri(videoUrl)
    }

    // Set up ExoPlayer with the media source
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Release ExoPlayer when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Create an AndroidView for displaying the PlayerView
    AndroidView(
        // Create the PlayerView and set the ExoPlayer
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        // Set the layout parameters for the PlayerView
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}
