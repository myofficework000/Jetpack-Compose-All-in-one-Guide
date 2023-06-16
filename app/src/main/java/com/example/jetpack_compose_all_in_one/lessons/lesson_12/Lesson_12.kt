package com.example.jetpack_compose_all_in_one.lessons.lesson_12

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpack_compose_all_in_one.utils.Constants.YOUTUBE_URL

@Preview
@Composable
fun Lesson_12() {
    LessonContent(context = LocalContext.current)
}

@Composable
fun LessonContent(context: Context) {

    Column {
        Text(text = "Web View Demo")
        LoadWebUrl(context, YOUTUBE_URL)

    }
}

@Composable
fun LoadWebUrl(context: Context, url: String) {

    AndroidView(factory = {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true // to enable youtube

            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}