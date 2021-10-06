package com.example.task5

import android.widget.ImageView
import coil.ImageLoader
import coil.request.ImageRequest

fun ImageView.loadWithLoader(
    imageLoader: ImageLoader,
    url: String,
    placeholder: Int? = null
) {
    val request = if (placeholder != null) {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .placeholder(placeholder)
            .target(this)
            .build()
    } else {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .target(this)
            .build()
    }
    val disposable = imageLoader.enqueue(request)
}
