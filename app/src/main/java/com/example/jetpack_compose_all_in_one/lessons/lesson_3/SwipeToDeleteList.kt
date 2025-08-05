package com.example.jetpack_compose_all_in_one.lessons.lesson_3

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteScreen(viewModel: SwipeToDeleteViewModel = hiltViewModel()) {
    val personList = viewModel.personList.collectAsState()

    val contextForToast = LocalContext.current.applicationContext

    LaunchedEffect(key1 = true) {
        viewModel.getPersonList()
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = personList.value,
            key = { person ->
                person.id
            }
        ) { person ->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                viewModel.deletePerson(person)
                Toast.makeText(contextForToast, "Deleted ${person.name}", Toast.LENGTH_SHORT).show()
            }
            SwipeToDismiss(
                state = dismissState,
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                background = {
                    val backgroundColor by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.DismissedToStart -> Color.Red.copy(alpha = 0.8f)
                            else -> Color.White
                        }, label = "Swipe to delete background color"
                    )

                    val iconImageVector = Icons.Outlined.Delete

                    val iconAlignment = Alignment.CenterEnd

                    val iconScale by animateFloatAsState(
                        targetValue = if (dismissState.targetValue == DismissValue.Default) 0.5f else 1.3f,
                        label = ""
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color = backgroundColor)
                            .padding(start = 16.dp, end = 16.dp),
                        contentAlignment = iconAlignment
                    ) {
                        Icon(
                            modifier = Modifier.scale(iconScale),
                            imageVector = iconImageVector,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                dismissContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = person.name,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp, bottom = 16.dp, start = 12.dp)
                        )
                    }
                }
            )
        }
    }
}