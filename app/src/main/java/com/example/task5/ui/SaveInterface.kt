package com.example.task5.ui

import android.graphics.Bitmap

interface SaveInterface {
    fun savePhotoToExternalStorage(displayName: String, bmp: Bitmap): Boolean
}
