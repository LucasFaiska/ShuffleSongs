package com.lfaiska.shufflesongs.presentation.scenes.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlayListUseCase

open class HomeViewModel(private val playListUseCase: PlayListUseCase) : ViewModel() {

    val songListMutableLiveData = MutableLiveData<List<Song>>()

    var songList = mutableListOf<Song>()
        set(value) {
            field = value
            updateSongsOnView(value)
        }

    fun updateSongsOnView(songList: List<Song>) {
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

    fun shuffleSongs(): MutableList<Song> {
        val shuffledSongs = mutableListOf<Song>()

        while (songList.isNotEmpty()) {
            val songToBeShuffled = songList.random()

            if (shuffledSongs.isEmpty() || !isSameArtist(songToBeShuffled, shuffledSongs.last())) {
                shuffledSongs.add(songToBeShuffled)
                songList.remove(songToBeShuffled)
            }
        }

        return shuffledSongs
    }

    fun isSameArtist(songSource: Song, songDest: Song) = songSource.artist.id == songDest.artist.id
}