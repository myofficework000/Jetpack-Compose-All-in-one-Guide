package com.example.jetpack_compose_all_in_one.ui.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

/*

@Composable
fun SnackBarView() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        coroutineScope.launch {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = "This is your message",
                actionLabel = "Do something"
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> Log.i("pmh", "hello")
                SnackbarResult.ActionPerformed -> Log.i("pmh", "hello")
            }
        }
}) {
    Text(text = "Click me!")
},
content = {
    innerPadding ->
    Text(
        text = "Body content",
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .wrapContentSize()
    )
}
)
}
*/


@Composable
fun ShowSnackbar(isOffline: Boolean, contentMessage: String, snackbarHostState: SnackbarHostState) {
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = contentMessage,
                duration = androidx.compose.material3.SnackbarDuration.Indefinite,
            )
        }
    }
}



