package com.lfaiska.shufflesongs.presentation.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.lfaiska.shufflesongs.R

class RoundedImageView(context: Context, val attributeSet: AttributeSet): ImageView(context, attributeSet) {

    private var imageUrl: String? = null

    init {
        loadAttributes()
        loadBitmapFromUrl()
    }

    private fun loadAttributes() {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView)
        imageUrl = attributes.getString(R.styleable.RoundedImageView_imageUrl)
        attributes.recycle()
    }

    private fun loadBitmapFromUrl() {
        DownloadImageTask { bitmap -> this.setImageBitmap(bitmap) }.execute(imageUrl)
    }

    override fun onDraw(canvas: Canvas) {
        if (width == 0 || height == 0) {
            return
        }

        drawable?.let {
            val bitmap = it.getBitmap().copy(Bitmap.Config.ARGB_8888, true)
            canvas.drawBitmap(getRoundedBitmap(bitmap, width), 0F, 0F, null)
        }
    }

    private fun Drawable.getBitmap() = (this as BitmapDrawable).bitmap

    private fun getRoundedBitmap(inputBitmap: Bitmap, radius: Int): Bitmap {
        val rect = Rect(0, 0, radius, radius)
        val paint = Paint()
        val roundedBitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888)
        val roundedBitmapCanvas = buildRoundedBitmapCanvas(roundedBitmap, paint, radius)
        roundedBitmapCanvas.drawBitmap(getBitmapScaled(inputBitmap, radius), rect, rect, paint)
        return roundedBitmap
    }

    private fun buildRoundedBitmapCanvas(bitmap: Bitmap, paint: Paint, radius: Int): Canvas {
        val canvas = Canvas(bitmap)
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(radius / 2f, radius / 2f, radius / 2f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        return canvas
    }

    private fun getBitmapScaled(bitmap: Bitmap, radius: Int): Bitmap {
        with(bitmap) {
            return if (width != radius || height != radius) {
                val smallest = Math.min(width, height).toFloat()
                val factor = smallest / radius
                Bitmap.createScaledBitmap(this,
                        (width / factor).toInt(),
                        (height / factor).toInt(), false)
            } else {
                this
            }
        }
    }
}