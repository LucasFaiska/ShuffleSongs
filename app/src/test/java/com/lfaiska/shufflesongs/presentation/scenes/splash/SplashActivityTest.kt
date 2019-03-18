package com.lfaiska.shufflesongs.presentation.scenes.splash

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import android.content.Intent
import com.lfaiska.shufflesongs.presentation.scenes.home.HomeActivity
import org.junit.Assert.assertEquals
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {

    lateinit var subject: SplashActivity

    @Before
    fun setup() {
        subject = Robolectric.buildActivity(SplashActivity::class.java).create().get()
    }

    @Test
    fun whenNavigateToHome_mustCallHomeActivty() {
        subject.navigateToHome()
        val expectedIntent = Intent(subject, HomeActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
}