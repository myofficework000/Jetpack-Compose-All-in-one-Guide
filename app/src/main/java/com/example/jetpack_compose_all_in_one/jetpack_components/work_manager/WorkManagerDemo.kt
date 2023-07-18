package com.example.jetpack_compose_all_in_one.jetpack_components.work_manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun WorkManagerDemoUI(
    workManager: WorkManager
) {
    val periodicWork by workManager
        .getWorkInfosForUniqueWorkLiveData(PeriodicWorker.uniqueWorkName)
        .observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { startOneTimeWork(workManager) }) {
            Text(text = "Start One-Time Work")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (
            periodicWork?.any {
                it.state == WorkInfo.State.RUNNING ||
                it.state == WorkInfo.State.ENQUEUED
            } == true
        ) {
            Button(onClick = { workManager.cancelUniqueWork(PeriodicWorker.uniqueWorkName) }) {
                Text(text = "Stop Periodic Work")
            }
        } else {
            Button(onClick = { startPeriodicWork(workManager) }) {
                Text(text = "Start Periodic Work")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { startChainableWork(workManager) }) {
            Text(text = "Start Chainable Work")
        }
    }
}

private fun startOneTimeWork(workManager: WorkManager) {
    workManager
        .beginUniqueWork(
            OneTimeWorker.uniqueWorkName,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<OneTimeWorker>()
                .addTag(OneTimeWorker.uniqueWorkTag)
                .build()
        )
        .enqueue()
}

private fun startPeriodicWork(workManager: WorkManager) {
    workManager.enqueueUniquePeriodicWork(
        PeriodicWorker.uniqueWorkName,
        ExistingPeriodicWorkPolicy.KEEP,
        PeriodicWorker.getWorkRequest(
            LocalDateTime.now().run {
                // How this works:
                //  1. Use Duration to get the delta from now() to the next hour
                //  2. Use truncatedTo() to set minute and second to 0.
                //  3. Use toMillis() to return the delta in milliseconds.
                Duration.between(this, plusHours(1)
                    .truncatedTo(ChronoUnit.HOURS))
                    .toMillis()
            }
        )
    )
}

private fun startChainableWork(workManager: WorkManager) {
    workManager
        .beginWith(chainableWorkRequest1)
        .then(chainableWorkRequest2)
        .then(chainableWorkRequest3)
        .then(chainableWorkRequest4)
        .enqueue()
}