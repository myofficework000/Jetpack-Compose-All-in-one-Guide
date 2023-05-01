package com.example.jetpack_compose_all_in_one.ui.views.quotes_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.sp_10
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.sp_36

/*@Composable
fun QuoteCard(quote: String, author: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(0.5.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp_10),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = quote,
                fontWeight = FontWeight.Bold,
                fontSize = sp_16,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = author,
                fontSize = sp_10,
                textAlign = TextAlign.Center
            )
        }
    }
}*/

@Composable
fun QuoteCard(
    quote: String,
    author: String,
    modifier: Modifier = Modifier
        .fillMaxWidth(0.8f)
        .fillMaxHeight(0.6f),
    cardPadding: PaddingValues = PaddingValues(16.dp)
) {
    Card(
        modifier = modifier.then(Modifier.padding(dp_15)),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(0.5.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(cardPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("And I quote...")

            Box(
                Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\" $quote \"",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = sp_32,
                    lineHeight = sp_36
                )
            }

            Text(text = "- $author",
                fontSize = sp_16
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuoteCardPreview() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        QuoteCard(
            "The power of water is its ability to take any shape...",
            "Rhodeia of Loch"
        )
    }
}