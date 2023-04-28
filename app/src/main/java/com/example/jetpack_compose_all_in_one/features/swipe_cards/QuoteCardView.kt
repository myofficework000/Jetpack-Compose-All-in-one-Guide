package com.example.jetpack_compose_all_in_one.features.swipe_cards

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.views.quotes_ui.QuoteViewModel
import com.example.jetpack_compose_all_in_one.utils.copyToClipboard

@Composable
fun DetailCard(viewModel: QuoteViewModel, quote: String, author: String) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .clickable(onClick = {
                    context.copyToClipboard(
                        quote
                            .plus("")
                            .plus("- $author")
                    )
                    Toast
                        .makeText(context, "Quote copied!", Toast.LENGTH_SHORT)
                        .show()
                })
                .background(MaterialTheme.colors.primaryVariant)
                .padding(dp_50)
                .align(Alignment.Center)
                .padding(dp_10),
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(align = Alignment.Center),
                text = """ " """,
                style = typography.h4,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(align = Alignment.Center),
                text = quote.ifBlank { "no_quotes_found" },
                style = typography.h5,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = author.ifBlank { "text_unknown" },
                style = typography.body1,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}