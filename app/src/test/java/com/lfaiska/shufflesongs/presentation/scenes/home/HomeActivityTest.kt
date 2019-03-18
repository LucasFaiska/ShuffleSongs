package com.lfaiska.shufflesongs.presentation.scenes.home

import android.view.View
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule

@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {

    @Mock
    lateinit var viewModel: HomeViewModel

    lateinit var subject: HomeActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        subject = Robolectric.buildActivity(HomeActivity::class.java).create().get()
        subject.viewModel = viewModel
    }

    @Test
    fun whenHideProgress_ProgressBarVisibilityMustBeGone() {
        subject.hideProgress()
        Assert.assertEquals(View.GONE, subject.binding.progressBar.visibility)
    }
}