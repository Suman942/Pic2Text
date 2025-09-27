package com.suman.pic2text.data

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class DataExtraction {
    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    fun extraction(bitmap: Bitmap, onResult: (String) -> Unit, onError: (Exception) -> Unit) {
        val imageBitmap = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(imageBitmap)
            .addOnSuccessListener {
                onResult.invoke(it.text)
            }
            .addOnFailureListener {
                onError.invoke(it)
            }
    }
}