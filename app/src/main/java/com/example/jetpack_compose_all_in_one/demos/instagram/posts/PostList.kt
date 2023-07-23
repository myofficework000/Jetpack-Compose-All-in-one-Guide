package com.example.jetpack_compose_all_in_one.demos.instagram.posts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.data.model.Tweet
import com.example.jetpack_compose_all_in_one.demos.instagram.posts.PostItem
import kotlin.random.Random

@Composable
fun PostList(
    posts: List<Tweet>,
    onLikeClicked: () -> Unit,
    onCommentsClicked: () -> Unit,
    onSendClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(posts) {
            PostItem(
                post = it,
                isLiked = Random.nextBoolean(),
                onLikeClicked = onLikeClicked,
                onCommentsClicked = onCommentsClicked,
                onSendClicked = onSendClicked
            )
        }
    }
}