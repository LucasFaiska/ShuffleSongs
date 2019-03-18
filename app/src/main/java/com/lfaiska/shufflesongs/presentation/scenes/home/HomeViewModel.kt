package com.lfaiska.shufflesongs.presentation.scenes.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlaylistUseCase

class HomeViewModel : ViewModel() {

    val songs = MutableLiveData<List<Song>>()

    fun loadSongs() {
        PlaylistUseCase().downloadPlaylist {
            songs.value = it
        }
    }
}