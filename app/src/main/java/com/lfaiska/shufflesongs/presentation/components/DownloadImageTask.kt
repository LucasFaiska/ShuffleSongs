package com.lfaiska.shufflesongs.presentation.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.webkit.URLUtil

class DownloadImageTask(val handler: (bitmap: Bitmap) -> Unit) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageUrl = urls.first()
        var downloadedBitmap: Bitmap? = null

        if (URLUtil.isValidUrl(imageUrl)) {
            try {
                val inputStream = java.net.URL(imageUrl).openStream()
                downloadedBitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.e("Error:", e.message)
                e.printStackTrace()
            }
        }

        return downloadedBitmap
    }

    override fun onPostExecute(result: Bitmap) {
        handler(result)
    }
}