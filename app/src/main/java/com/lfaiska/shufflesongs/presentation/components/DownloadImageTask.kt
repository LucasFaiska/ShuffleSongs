package com.lfaiska.shufflesongs.presentation.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.webkit.URLUtil

class DownloadImageTask(val handler: (bitmap: Bitmap?) -> Unit) : AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageUrl = urls.first()
        return if (ImageCache.hasImageCache(imageUrl)) {
            ImageCache.getImage(imageUrl)
        } else {
            performImageDownload(imageUrl)
        }
    }

    private fun performImageDownload(imageUrl: String): Bitmap? {
        if (URLUtil.isValidUrl(imageUrl)) {
            try {
                val inputStream = java.net.URL(imageUrl).openStream()
                val downloadedBitmap = BitmapFactory.decodeStream(inputStream)
                ImageCache.addImage(imageUrl, downloadedBitmap)
                return downloadedBitmap
            } catch (e: Exception) {
                Log.e("Error:", e.message)
                e.printStackTrace()
            }
        }

        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        handler(result)
    }
}