package com.example.jetpack_compose_all_in_one.demos.instagram

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.data.model.Tweet
import com.example.jetpack_compose_all_in_one.demos.DemoDataProvider
import com.example.jetpack_compose_all_in_one.demos.instagram.posts.PostList
import com.example.jetpack_compose_all_in_one.demos.instagram.stories.StoryList
import com.example.jetpack_compose_all_in_one.demos.instagram.stories.StoryPopup

@Composable
fun InstagramHome(
    posts: List<Tweet>,
    profiles: List<Tweet>,
    onLikeClicked: () -> Unit,
    onCommentsClicked: () -> Unit,
    onSendClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onMessagingClicked: () -> Unit
) {
    val showStory = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Instagram") },
                    backgroundColor = MaterialTheme.colors.surface,
                    contentColor = MaterialTheme.colors.onSurface,
                    elevation = 8.dp,
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_instagram),
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = onMessagingClicked) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_send),
                                contentDescription = "Go to messaging screen",
                            )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Surface {
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        StoryList(
                            profiles = profiles,
                            onProfileClicked = {
                                showStory.value = true
                                onProfileClicked.invoke()
                            }
                        )
                        Divider()
                        PostList(
                            posts = posts,
                            onLikeClicked = onLikeClicked,
                            onCommentsClicked = onCommentsClicked,
                            onSendClicked = onSendClicked
                        )
                    }
                }
            }
        )
        AnimatedVisibility(
            visible = showStory.value,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            StoryPopup(imageIds = DemoDataProvider.itemList.take(5)) {
                showStory.value = false
            }
        }
    }
}

@Preview
@Composable
fun PreviewInstagramHome() {
    InstagramHome(
        posts = DemoDataProvider.tweetList.filter { it.tweetImageId != 0 },
        profiles = DemoDataProvider.tweetList,
        onLikeClicked = {},
        onCommentsClicked = {},
        onSendClicked = {},
        onProfileClicked = {},
        onMessagingClicked = {}
    )
}