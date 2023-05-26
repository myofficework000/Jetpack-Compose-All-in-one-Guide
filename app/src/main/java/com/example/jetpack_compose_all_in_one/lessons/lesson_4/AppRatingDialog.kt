package com.example.jetpack_compose_all_in_one.lessons.lesson_4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.GradientButton
import com.example.jetpack_compose_all_in_one.ui.theme.ShadowColor
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import kotlinx.coroutines.delay

//Assigned to Luan
@Composable
fun AppRatingDialog() {
    var showPopUp by remember { mutableStateOf(true) }
    var showSnackBar by remember { mutableStateOf(false) }

    val onPopupDismissed = {
        showPopUp = false
        showSnackBar = true
    }
    var comment by remember { mutableStateOf("") }

    if(showPopUp){
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.rate_app_title),
                    textAlign = TextAlign.Center
                )
            },
            text ={
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dp_10)
                ) {
                    Text(
                        text = stringResource(R.string.rate_app_text),
                        textAlign = TextAlign.Center
                    )
                    RatingStar()
                    TextField(
                        value = comment, 
                        onValueChange = {
                            comment = it
                        },
                        placeholder = {
                            Text(text = stringResource(id = R.string.rate_app_comment))
                        },
                    )
                }
            },
            confirmButton = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                ){
                    GradientButton(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.submit_rating),
                        textColor = Color.White,
                        gradient = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFD4145A),
                                Color(0xFFFBB03B)
                            )
                        ),
                        onClick = onPopupDismissed
                    )
                }
            }
        )
    }

    if(showSnackBar){
        LaunchedEffect(true){
            delay(3000L)
            showSnackBar = false
        }
        Box(modifier = Modifier.fillMaxSize()){
            Snackbar(
                modifier = Modifier.padding(dp_15)
            ) {
                Text(text = stringResource(id = R.string.rate_app_submit_toast))
            }
        }

    }
}

@Composable
fun RatingStar() {
    var rating by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        repeat(5){ index ->
            val isFilled = rating > index
            val starIcon = if (isFilled) Icons.Filled.Star else Icons.Outlined.StarBorder
            Icon(
                imageVector = starIcon,
                contentDescription = R.string.rate_icon.toString(),
                modifier = Modifier.clickable {
                    rating = if (isFilled) {
                        index
                    } else {
                        index + 1
                    }
                }
            )
        }
    }
}
