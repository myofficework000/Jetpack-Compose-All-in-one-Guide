package com.example.jetpack_compose_all_in_one.application_components.content_provider.demo_images

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
import java.io.File

// This composable function displays images from the device's external storage.
// It uses Jetpack Compose for UI and Glide for image loading.

// Annotations to opt into experimental features for Glide Compose API and Permissions API.
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ShowImages(
    // Default parameter specifying the maximum number of images to display.
    limit: Int = 10
) {
    // Retrieves the current context using the LocalContext composable.
    val context = LocalContext.current

    // Creates a remembered mutable state list to store paths of images.
    val imagePaths = remember { mutableStateListOf<String>() }

    // Creates a remembered mutable state variable to track whether the necessary permissions are granted.
    var isPermissionGranted by remember { mutableStateOf(false) }

    // Requests read permission and updates the isPermissionGranted variable accordingly.
    val readPermissionRequester = requestReadPerm { isPermissionGranted = it }

    // Launched effect that triggers when isPermissionGranted value changes.
    LaunchedEffect(isPermissionGranted) {
        if (isPermissionGranted) {
            // Clears the list of image paths.
            imagePaths.clear()

            // Queries the device's external storage for image paths.
            context.contentResolver.query(
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
                // Retrieves the column index for image data.
                val pathColumnIndex = getColumnIndex(MediaStore.Images.ImageColumns.DATA)

                // Iterates over the query result and adds image paths to the list.
                var remainingLimit = limit
                while (moveToNext() && remainingLimit >= 0) {
                    if (pathColumnIndex >= 0) {
                        imagePaths.add(getString(pathColumnIndex))
                    }
                    remainingLimit--
                }
            }?.close()
        }
    }

    // Displays images if permission is granted, otherwise displays a button to request permission.
    if (isPermissionGranted) {
        LazyColumn(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(imagePaths) { imagePath ->
                // Uses GlideImage composable to load and display images.
                GlideImage(File(imagePath), "")
            }
        }
    } else {
        // Displays a button to request permission to access external storage.
        SimpleTextButton("Show Images") { readPermissionRequester.launchPermissionRequest() }
    }
}
