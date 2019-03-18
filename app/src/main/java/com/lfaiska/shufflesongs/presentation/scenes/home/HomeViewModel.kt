package com.lfaiska.shufflesongs.presentation.scenes.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlayListUseCase

class HomeViewModel(val playListUseCase: PlayListUseCase) : ViewModel() {

    val songListMutableLiveData = MutableLiveData<List<Song>>()

    private var songList: MutableList<Song>? = null
        set(value) {
            value?.let {
                updateSongsOnView(it)
            }
            field = value
        }

    private fun updateSongsOnView(songList: List<Song>) {
        songListMutableLiveData.value = songList
    }

    fun loadSongs() {
        playListUseCase.downloadPlaylist {
            songList = it.toMutableList()
        }
    }

    fun onShuffleButtonTouched() {
        songList = shuffleSongs()
    }

    private fun shuffleSongs(): MutableList<Song>? {
        val shuffledSongs = mutableListOf<Song>()

        songList?.let { songList ->
            while (songList.isNotEmpty()) {
                val songToBeShuffled = songList.random()

                if (shuffledSongs.isEmpty() || !isSameArtist(songToBeShuffled, shuffledSongs.last())) {
                    shuffledSongs.add(songToBeShuffled)
                    songList.remove(songToBeShuffled)
                }
            }
        }

        return shuffledSongs
    }

    private fun isSameArtist(songSource: Song, songDest: Song) = songSource.artist.id == songDest.artist.id
}