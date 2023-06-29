package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}