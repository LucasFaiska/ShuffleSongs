package com.lfaiska.shufflesongs.presentation.components

import android.graphics.Bitmap
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ImageCacheTest {

    val mockedUrl = "http://test.co"

    @Mock
    lateinit var bitmap: Bitmap

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun whenCallsHasImageCache_andImageExists_mustReturnsTrue() {
        ImageCache.addImage(mockedUrl, bitmap)
        assertEquals(true, ImageCache.hasImageCache(mockedUrl))
    }

    @Test
    fun whenCallsHasImageCache_andImageNotExists_mustReturnsFalse() {
        assertEquals(false, ImageCache.hasImageCache(mockedUrl))
    }

}