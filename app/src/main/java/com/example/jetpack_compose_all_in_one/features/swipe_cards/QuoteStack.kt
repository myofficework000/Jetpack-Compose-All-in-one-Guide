package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.utils.InfiniteList
import com.example.jetpack_compose_all_in_one.view.QuoteCard

@Composable
fun <T> QuoteStack(
    modifier: Modifier = Modifier,
    items: InfiniteList<T>,
    onReject: (T) -> Unit = {},
    onLike: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit,
) {
    var currentPageNum by remember{ mutableStateOf(0) }
    val swipeState = rememberSwipeableState(
        onLeft = { items.current?.let{
            onReject(it)
            currentPageNum = items.moveToNext()
        } },
        onRight = { items.current?.let{
            onLike(it)
            currentPageNum = items.moveToNext()
        } }
    )

    items[currentPageNum+1]?.let { profile ->
        StackBackgroundCard(modifier, swipeState) { itemContent(profile) }
    }
    items[currentPageNum]?.let { profile ->
        StackForegroundCard(modifier, swipeState) { itemContent(profile) }
    }
}

@Preview
@Composable
fun StackPreview() {
    val items = InfiniteList(listOf("12345","23456","34567","45678"))
    QuoteStack(items = items) {QuoteCard(it,"-Nobody")}
}