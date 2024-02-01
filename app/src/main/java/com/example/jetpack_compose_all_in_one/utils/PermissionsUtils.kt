package com.example.jetpack_compose_all_in_one.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

/*
* Notes:
*   1. These composable doesn't start the request when defined. Remember to launch them.
* */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestReadPerm(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES
    else
        Manifest.permission.READ_EXTERNAL_STORAGE
) { isGranted(it) }

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestCoarseLocation(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.ACCESS_COARSE_LOCATION
) { isGranted(it) }

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestFineLocation(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.ACCESS_FINE_LOCATION
) { isGranted(it) }

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestAllLocation(isGranted: (Boolean) -> Unit) = rememberMultiplePermissionsState(
    listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
) { isGranted(it.all { x -> x.value }) }

fun isLocationAllowed(context: Context) =
    ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ||
    ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestCamera(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.CAMERA
) { isGranted(it) }

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestReadContacts(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.READ_CONTACTS
) { isGranted(it) }

/*
    implementation to check if sms permission is enabled so
    broadcast can notify user if they received SMS
*/
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun isSmsPermissionEnabled(isGranted: (Boolean) -> Unit) = rememberPermissionState(
    Manifest.permission.RECEIVE_SMS
) { isGranted(it) }