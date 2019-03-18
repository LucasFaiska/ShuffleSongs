package com.lfaiska.shufflesongs.presentation.scenes.song

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SongViewModelTest {

    lateinit var viewModel: SongViewModel

    @Before
    fun setup() {
        viewModel = SongViewModel("http://artwork.jpg", "Some Cool Name", "Song name", "Song Genre")
    }

    @Test
    fun whenGetFormattedGenre_mustBeFormat() {
        assertEquals("(Song Genre)", viewModel.formattedGenre)
    }

}