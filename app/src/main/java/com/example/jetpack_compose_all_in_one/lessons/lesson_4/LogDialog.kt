package com.example.jetpack_compose_all_in_one.lessons.lesson_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.jetpack_compose_all_in_one.ui.theme.Green700
import com.example.jetpack_compose_all_in_one.ui.theme.Red500
import com.example.jetpack_compose_all_in_one.ui.theme.dp_30
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.views.main_ui.MainActivity

//Assign to Alex
@Composable
fun LogoutDialogUI() {
    KillApp()
}


@Composable
fun KillApp() {
    val activity = (LocalContext.current as? MainActivity)

    Column( 
        modifier = Modifier
            .fillMaxSize()
            .padding(dp_30),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val openDialog = remember { mutableStateOf(false)}
        Button(
            onClick = { openDialog.value = true } ,
            colors = ButtonDefaults.buttonColors(Green700)
        ) {
            Text(
                text = "Logout",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontSize = sp_16,
            )
        }
        if(openDialog.value) {
            AlertDialog(
                onDismissRequest = {

                    openDialog.value = false
                },
                title = {
                    Text(
                        text = "Logout",
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Monospace,
                        fontSize = sp_32)
                },
                text = {
                    Text(
                        text = "Are you sure you want to close the application?",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Justify,
                        fontSize = sp_16
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            activity?.finish()
                        },
                        colors = ButtonDefaults.buttonColors(Red500)
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}
