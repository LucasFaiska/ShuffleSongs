package com.lfaiska.shufflesongs.presentation.scenes.song

import android.arch.lifecycle.ViewModel

class SongViewModel(
        val artWork: String,
        val artistName: String,
        val name: String,
        val genre: String
): ViewModel() {

    val formattedGenre: String
        get() = "($genre)"

}