package com.example.jetpack_compose_all_in_one.demos.tiktok.components.home

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TikTokPlayer(context: Context, url: String, selected: Boolean) {
    val tiktokPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val mediaSource = ProgressiveMediaSource
                    .Factory(
                        DefaultDataSourceFactory(context, "composeCookBook")
                    )
                    .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${url}")))

                this.setMediaSource(mediaSource, true)
                this.prepare()
            }
    }
    tiktokPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    tiktokPlayer.repeatMode = Player.REPEAT_MODE_ONE
    AndroidView({
        PlayerView(it).apply {
            useController = false
            player = tiktokPlayer
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    })

    tiktokPlayer.playWhenReady = selected

    DisposableEffect(key1 = url) {
        onDispose {
            tiktokPlayer.release()
        }
    }
}