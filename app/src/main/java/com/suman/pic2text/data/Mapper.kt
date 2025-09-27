package com.suman.pic2text.data

import android.content.Context
import android.graphics.Bitmap
import androidx.exifinterface.media.ExifInterface
import android.graphics.Matrix
import android.net.Uri

fun Bitmap.rotateIfRequired(context: Context, uri: Uri): Bitmap {
    val inputStream = context.contentResolver.openInputStream(uri)
    val exif = ExifInterface(inputStream!!)
    val orientation = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )
    inputStream.close()

    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
    }

    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}
