package com.example.jetpack_compose_all_in_one.demos.barcode_scanner

import android.util.Size
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

/**
 * ScanMode is an enum class representing different scan modes supported by the camera preview.
 * It includes options for scanning QR codes, barcodes, or both.
 */
enum class ScanMode {
    QR_CODE,
    BARCODE,
    BOTH
}

/**
 * CameraPreview is a composable function used to display a camera preview for scanning QR codes and barcodes.
 * It leverages the CameraX API for camera management and the ML Kit Barcode Scanning API for barcode detection.
 *
 * @param modifier The modifier for the camera preview.
 * @param onBarcodeDetected Callback function to handle the detected barcode.
 * @param scanMode The scan mode to specify the type of codes to be scanned (default: ScanMode.BOTH).
 */
@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@androidx.camera.core.ExperimentalGetImage
fun CameraPreview(
    modifier: Modifier,
    onBarcodeDetected: (Barcode) -> Unit,
    scanMode: ScanMode = ScanMode.BOTH
) {
    // Retrieve the current context and lifecycle owner
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as LifecycleOwner

    // Executor for camera operations
    val cameraExecutor = Executors.newSingleThreadExecutor()

    // Configure barcode scanner options based on the specified scan mode
    val options = when (scanMode) {
        ScanMode.QR_CODE -> BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()

        ScanMode.BARCODE -> BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_CODE_128, Barcode.FORMAT_CODE_39)
            .build()

        ScanMode.BOTH -> BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }

    // Initialize the barcode scanner client
    val barcodeScanner = BarcodeScanning.getClient(options)

    // Create an AndroidView to integrate CameraX preview
    AndroidView(
        modifier = modifier,
        factory = { context ->
            // Create a PreviewView to display the camera preview
            val previewView = PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            // Obtain the camera provider
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                // Configure the camera preview
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                // Configure image analysis for barcode detection
                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(1280, 720))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor) { imageProxy ->
                            val mediaImage = imageProxy.image
                            if (mediaImage != null) {
                                val image = InputImage.fromMediaImage(
                                    mediaImage,
                                    imageProxy.imageInfo.rotationDegrees
                                )
                                // Process the image for barcode detection
                                barcodeScanner.process(image)
                                    .addOnSuccessListener { barcodes ->
                                        // Invoke callback function for each detected barcode
                                        for (barcode in barcodes) {
                                            onBarcodeDetected(barcode)
                                        }
                                    }
                                    .addOnCompleteListener {
                                        // Close the image proxy after processing
                                        imageProxy.close()
                                    }
                            }
                        }
                    }

                // Bind the camera preview and image analysis to the camera lifecycle
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))

            previewView
        }
    )
}
