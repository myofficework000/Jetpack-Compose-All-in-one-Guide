package com.example.jetpack_compose_all_in_one.demos.barcode_scanner

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * ScannerApp is a composable function that represents the main screen of the scanner application.
 * This function integrates camera preview for scanning QR codes and barcodes and displays the last scanned code.
 */
@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun ScannerApp() {
    // State variables to track scan mode, last scanned code, and camera permission status
    var scanMode by remember { mutableStateOf(ScanMode.BOTH) }
    var lastScannedCode by remember { mutableStateOf("") }
    var hasCameraPermission by remember { mutableStateOf(false) }

    // Request camera permission and update state based on the result
    val permissionResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { hasCameraPermission = it }
    )

    // Request camera permission when the component is launched
    LaunchedEffect(key1 = true) {
        permissionResult.launch(Manifest.permission.CAMERA)
    }

    // Column layout to organize UI components vertically
    Column {
        // Row to display scan mode options (QR code, barcode, both)
        Row {
            Text(text = "QR Code")
            RadioButton(selected = scanMode == ScanMode.QR_CODE, onClick = { scanMode = ScanMode.QR_CODE })
            Text(text = "Barcode")
            RadioButton(selected = scanMode == ScanMode.BARCODE, onClick = { scanMode = ScanMode.BARCODE })
            Text(text = "Both")
            RadioButton(selected = scanMode == ScanMode.BOTH, onClick = { scanMode = ScanMode.BOTH })
        }
        // Check camera permission status and display camera preview or permission message accordingly
        if (hasCameraPermission) {
            // Display camera preview for scanning
            CameraPreview(
                modifier = Modifier.weight(1f),
                onBarcodeDetected = { barcode ->
                    // Update last scanned code when a barcode is detected
                    lastScannedCode = barcode.rawValue ?: "No barcode data"
                },
                scanMode = scanMode)
            // Display the last scanned code
            Text(text = "Last Scanned Code: $lastScannedCode")
        } else {
            // Display message when camera permission is not granted
            Text(text = "Camera permission not granted")
        }
    }
}
