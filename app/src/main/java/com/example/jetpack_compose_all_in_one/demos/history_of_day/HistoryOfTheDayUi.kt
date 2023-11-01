package com.example.jetpack_compose_all_in_one.demos.history_of_day

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.technocraze.mvvmdatehistroy.viewmodel.HistoryViewModel

@Composable
fun HistoryOfTheDayUI(viewModel: HistoryViewModel = hiltViewModel()) {
  val state by viewModel.historyState.collectAsState()
  ConstraintLayout {
    val (historyList, progressBar) = createRefs()
    if (state.isLoading) {
      Box(modifier = Modifier
        .fillMaxSize()
        .constrainAs(progressBar) {
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        }, contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    } else if (state.errorMessage.isNotEmpty()) {
      Box(modifier = Modifier
        .fillMaxSize()
        .constrainAs(progressBar) {
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        }, contentAlignment = Alignment.Center
      ) {
        Text(text = state.errorMessage)
      }
    } else {

      Box(modifier = Modifier
        .fillMaxSize()
        .constrainAs(historyList) {
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        }) {
        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
          items(state.historyDates.size) { ind ->
            Card(
              modifier = Modifier
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(10.dp)),
              shape = CardDefaults.elevatedShape
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp)
              ) {
                Text(
                  text = state.historyDates.get(ind).date.trim(), style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                  )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = state.historyDates.get(ind).description.trim(), style = TextStyle(
                    fontWeight = FontWeight.Normal,
                  )
                )
              }
            }
          }
        }
      }
    }

  }
}