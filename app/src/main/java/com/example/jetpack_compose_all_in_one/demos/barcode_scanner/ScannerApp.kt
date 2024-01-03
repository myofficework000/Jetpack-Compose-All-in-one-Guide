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

@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun ScannerApp() {
    var scanMode by remember { mutableStateOf(ScanMode.BOTH) }
    var lastScannedCode by remember { mutableStateOf("") }
    var hasCameraPermission by remember { mutableStateOf(false) }

    val permissionResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { hasCameraPermission = it }
    )

    LaunchedEffect(key1 = true) {
        permissionResult.launch(Manifest.permission.CAMERA)
    }

    Column {
        Row {
            Text(text = "QR Code")
            RadioButton(selected = scanMode == ScanMode.QR_CODE, onClick = { scanMode = ScanMode.QR_CODE })
            Text(text = "Barcode")
            RadioButton(selected = scanMode == ScanMode.BARCODE, onClick = { scanMode = ScanMode.BARCODE })
            Text(text = "Both")
            RadioButton(selected = scanMode == ScanMode.BOTH, onClick = { scanMode = ScanMode.BOTH })
        }
        if (hasCameraPermission) {
            CameraPreview(
                modifier = Modifier.weight(1f),
                onBarcodeDetected = { barcode ->
                lastScannedCode = barcode.rawValue ?: "No barcode data"
            },
                scanMode = scanMode)
            Text(text = "Last Scanned Code: $lastScannedCode")
        } else {
            Text(text = "Camera permission not granted")
        }
    }
}