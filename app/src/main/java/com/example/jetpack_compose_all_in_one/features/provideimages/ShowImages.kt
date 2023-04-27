package com.example.jetpack_compose_all_in_one.features.provideimages

import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.utils.requestReadPerm
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ShowImages(
    limit: Int = 10
) {
    val ctx = LocalContext.current
    val imagePaths = remember{ mutableStateListOf<String>() }
    var permissionGranted by remember{ mutableStateOf(false) }
    val readPermission = requestReadPerm{ permissionGranted = it }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            imagePaths.clear()
            ctx.contentResolver.query(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
                else
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Images.ImageColumns.DATA
                ),
                null,
                null,
                null
            )?.apply {
                val pathColumn = getColumnIndex(MediaStore.Images.ImageColumns.DATA)

                var loadLimit = limit
                while (moveToNext() && loadLimit >= 0) {
                    if (pathColumn >= 0) {
                        imagePaths.add(getString(pathColumn))
                    }
                    loadLimit--
                }
            }?.close()
        }
    }

    if (permissionGranted) {
        LazyColumn(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(imagePaths) {
                GlideImage(File(it), "")
            }
        }
    } else {
        SimpleTextButton("Show Images") { readPermission.launchPermissionRequest() }
    }
}