package com.example.jetpack_compose_all_in_one.jetpack_components.work_manager

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder

internal val chainableWorkRequest1 = OneTimeWorkRequestBuilder<OneTimeWorker>()
    .setConstraints(
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    )
    .build()

internal val chainableWorkRequest2 = OneTimeWorkRequestBuilder<OneTimeWorker>()
    .setConstraints(
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    )
    .build()

internal val chainableWorkRequest3 = OneTimeWorkRequestBuilder<OneTimeWorker>()
    .setConstraints(
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    )
    .build()

internal val chainableWorkRequest4 = OneTimeWorkRequestBuilder<OneTimeWorker>()
    .setConstraints(
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    )
    .build()
