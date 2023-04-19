package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.jetpack_compose_all_in_one.R

@Composable
fun SimpleDialog(){
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = {showPopUp = false}

    if(showPopUp){
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = stringResource(R.string.dialog_title))
            },
            text ={
                Text(text = stringResource(R.string.dialog_text))
            },
            confirmButton = {
                Button(
                    onClick = onPopupDismissed,
                ) {
                    Text(text = stringResource(R.string.confirm_btn))
                }
            }
        )
    }
}
fun <T> ((T) -> Boolean).and(arg: (T) -> Boolean): (T) -> Boolean = { this(it) && arg(it) }

@Composable
fun DeleteDialog(onConfirmClicked:(close: Boolean) ->Unit){
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = {
        showPopUp = false
    }

    if(showPopUp){
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = stringResource(R.string.dialog_title))
            },
            text ={
                Text(text = stringResource(R.string.dialog_text))
            },
            confirmButton = {
                Button(
                    onClick = {
                        showPopUp = false
                        onConfirmClicked(true)
                    },
                ) {
                    Text(text = stringResource(R.string.confirm_btn))
                }
            }
        )
    }
}