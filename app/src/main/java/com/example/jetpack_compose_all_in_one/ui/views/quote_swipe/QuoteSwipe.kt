package com.example.jetpack_compose_all_in_one.ui.views.quote_swipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.jetpack_compose_all_in_one.features.swipe_cards.QuoteStack
import com.example.jetpack_compose_all_in_one.features.swipe_cards.QuoteSwipeViewModel
import com.example.jetpack_compose_all_in_one.ui.views.quotes_ui.QuoteCard

@Composable
fun QuoteSwipe(
    vm: QuoteSwipeViewModel
) {
    val quoteList = remember{ vm.quotesList }

    QuoteStack(items = quoteList) { data ->
        QuoteCard(data.content,data.author)
    }
}