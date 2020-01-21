package com.app.image_selector.utills

import android.content.Context
import android.database.Cursor
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Kumar Shubham
 * 20/1/20
 */

internal fun Cursor.doWhile(action: () -> Unit) {
    this.use {
        if (this.moveToFirst()) {
            do {
                action()
            } while (this.moveToNext())
        }
    }
}


@Throws(IOException::class)
internal fun createTempImageFile(context : Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        storageDir
    )
    return image
}