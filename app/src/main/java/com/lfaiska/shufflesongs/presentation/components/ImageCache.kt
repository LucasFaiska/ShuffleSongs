package com.lfaiska.shufflesongs.presentation.components

import android.graphics.Bitmap

object ImageCache {
    private val images = HashMap<String, Bitmap>()

    fun hasImageCache(imageUrl: String) = images.containsKey(imageUrl)

    fun getImage(imageUrl: String) = images.get(imageUrl)

    fun addImage(imageUrl: String, bitmap: Bitmap) {
        images[imageUrl] = bitmap
    }
}