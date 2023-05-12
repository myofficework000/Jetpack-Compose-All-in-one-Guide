package com.example.jetpack_compose_all_in_one.features.qrcodescanner

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarCodeAnalyser(
    val callback: () -> Unit
) : ImageAnalysis.Analyzer{
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        val scanner = BarcodeScanning.getClient(options)
        val mediaImage = imageProxy.image
        mediaImage?.let {
            val image = InputImage.fromMediaImage(mediaImage,imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if(barcodes.size > 0){
                        callback()
                    }
                }
                .addOnFailureListener{

                }
        }
        imageProxy.close()
    }

}