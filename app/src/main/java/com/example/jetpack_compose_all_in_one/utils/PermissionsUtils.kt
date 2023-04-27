package com.example.jetpack_compose_all_in_one.utils

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestReadPerm(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES
    else
        Manifest.permission.READ_EXTERNAL_STORAGE
) {isGranted(it) }