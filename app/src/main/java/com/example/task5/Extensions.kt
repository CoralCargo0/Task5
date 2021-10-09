package com.example.task5

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.mvvm.IMAGE_QUALITY
import com.example.task5.ui.sdk29AndUp
import java.io.IOException

fun Bitmap.savePhotoToExternalStorage(displayName: String, context: Context): Boolean {
    val imageCollection = sdk29AndUp {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "Cat$displayName.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.WIDTH, this@savePhotoToExternalStorage.width)
        put(MediaStore.Images.Media.HEIGHT, this@savePhotoToExternalStorage.height)
    }
    return try {
        context.contentResolver.insert(imageCollection, contentValues)?.also { uri ->
            context.contentResolver.openOutputStream(uri).use { outputStream ->
                if (!this.compress(Bitmap.CompressFormat.JPEG, IMAGE_QUALITY, outputStream)) {
                    throw IOException("Couldn't save bitmap")
                }
            }
        } ?: throw IOException("Couldn't create MediaStore entry")
        true
    } catch (e: IOException) {
        false
    }
}

fun RecyclerView.isLastItemDisplaying(): Boolean {
    if (this.adapter?.let { it.itemCount != 0 } == true) {
        val lastVisibleItemPosition =
            (this.layoutManager as GridLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
        if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition >= this.adapter!!
            .itemCount - 4
        ) return true
    }
    return false
}
