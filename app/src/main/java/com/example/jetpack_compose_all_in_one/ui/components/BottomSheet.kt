package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {

    when {
        sheetState.isVisible -> {
            ModalBottomSheet(
                modifier = Modifier.fillMaxSize(),
                onDismissRequest = onDismiss,
                sheetState = sheetState
            ) {
                Text(text = "Bottom Sheet Content")
            }
        }
    }
}


@Composable
fun MyBottomSheetContent(onDismiss: () -> Unit){
    Column( modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Bottom Sheet Content")
        Button(onClick = onDismiss) {
            Text(text = "Close")
        }
    }
}

@Composable
fun MyScreenContent(onOpenBottomSheet: () -> Unit){
    Column(modifier =  Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text(text = "Main Content")
        Button(onClick = onOpenBottomSheet) {
            Text(text = "Open Bottom Sheet")
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(){

    val bottomSheetState = remember {
        ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    }

    val coroutineScope = rememberCoroutineScope()

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
               MyBottomSheetContent(onDismiss = {
                   coroutineScope.launch{
                       bottomSheetState.hide()
                   }
               })
                           },
            content = {
                MyScreenContent(onOpenBottomSheet = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                })
            }
        )
}