package com.lfaiska.shufflesongs.network

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.assertEquals

@RunWith(JUnit4::class)
class PlaylistServiceTest {

    lateinit var subject: PlaylistService

    @Before
    fun setup() {
        subject = PlaylistService {  }
    }

    @Test
    fun testBuildRequestUrl() {
        val expectedValue = "https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id=1,2,3,4,5&limit5"
        val actualValue = subject.buildRequestUrl(arrayOf("1","2","3","4","5"))
        assertEquals(expectedValue, actualValue)
    }


}