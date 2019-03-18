package com.lfaiska.shufflesongs.presentation.scenes.home

import com.lfaiska.shufflesongs.domain.Artist
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlayListUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(JUnit4::class)
class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @Mock
    lateinit var playlistUseCase: PlayListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = spy(HomeViewModel(playlistUseCase))
    }

    @Test
    fun test_isSameArtist() {
        val songSrc = Song("http://artwork.jpg", Artist("123", "Artist Name"), "Song Name", "Genre")
        val songDest = Song("http://artwork.jpg", Artist("123", "Artist Name"), "Song Name", "Genre")
        Assert.assertEquals(true, viewModel.isSameArtist(songSrc, songDest))
    }

    @Test
    fun test_shuffle_songs() {
        val artistA = Artist("123", "Artist A")
        val artistB = Artist("456", "Artist B")

        viewModel.songList.add(Song("http://myart.jpg", artistA, "Song name", "Song Genre"))
        viewModel.songList.add(Song("http://myart.jpg", artistA, "Song name", "Song Genre"))
        viewModel.songList.add(Song("http://myart.jpg", artistB, "Song name", "Song Genre"))
        viewModel.songList.add(Song("http://myart.jpg", artistB, "Song name", "Song Genre"))

        val shuffledSongs = viewModel.shuffleSongs()

        Assert.assertEquals(false, viewModel.isSameArtist(shuffledSongs[0], shuffledSongs[1]))
        Assert.assertEquals(false, viewModel.isSameArtist(shuffledSongs[1], shuffledSongs[2]))
        Assert.assertEquals(false, viewModel.isSameArtist(shuffledSongs[2], shuffledSongs[3]))
    }
}