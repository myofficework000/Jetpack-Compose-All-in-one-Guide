package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.jetpack_compose_all_in_one.R

@Composable
fun AlertDialog() {
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = { showPopUp = false }

    if (showPopUp) {
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = stringResource(R.string.dialog_title))
            },
            text = {
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
fun DeleteDialog(onConfirmClicked: (close: Boolean) -> Unit) {
    var showPopUp by remember { mutableStateOf(true) }
    val onPopupDismissed = {
        showPopUp = false
    }

    if (showPopUp) {
        AlertDialog(
            onDismissRequest = onPopupDismissed,
            title = {
                Text(text = stringResource(R.string.dialog_title))
            },
            text = {
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

// The choices are the display name of each item.
// The output is the index the user chose.
@Composable
fun ChoiceDialog(
    currentChoice: Int,
    choices: List<String>,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onChoose: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        RadioButtons(
            choices,
            choices[currentChoice],
            Modifier
                .fillMaxWidth()
                .then(modifier)
        ) { onChoose(it); onDismiss() }
    }
}