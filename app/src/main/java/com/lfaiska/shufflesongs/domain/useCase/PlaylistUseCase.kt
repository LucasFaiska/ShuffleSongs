package com.lfaiska.shufflesongs.domain.useCase

import com.lfaiska.shufflesongs.domain.Song

interface PlayListUseCase {
    fun downloadPlaylist(handler: (songList: List<Song>) -> Unit)
}