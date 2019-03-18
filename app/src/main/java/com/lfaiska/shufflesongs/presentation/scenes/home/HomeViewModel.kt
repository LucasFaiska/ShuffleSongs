package com.lfaiska.shufflesongs.presentation.scenes.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlayListUseCase
import com.lfaiska.shufflesongs.domain.useCase.PlaylistUseCaseImpl

class HomeViewModel(val playListUseCase: PlayListUseCase) : ViewModel() {

    val songs = MutableLiveData<List<Song>>()

    fun loadSongs() {
        playListUseCase.downloadPlaylist {
            songs.value = it
        }
    }

    fun shuffleSongs() {

    }
}