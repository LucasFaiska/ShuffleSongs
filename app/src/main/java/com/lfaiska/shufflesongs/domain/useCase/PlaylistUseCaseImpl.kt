package com.lfaiska.shufflesongs.domain.useCase

import com.lfaiska.shufflesongs.domain.Artist
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.network.PlaylistService

class PlaylistUseCaseImpl: PlayListUseCase {

    override fun downloadPlaylist(handler: (songList: List<Song>) -> Unit) {
        PlaylistService { playlistResponse ->
            handler(playlistResponse.songList.map { songResponse ->
                with(songResponse) {
                    Song(artwork, Artist(artistId, artistName), name, genre)
                }
            })
        }.execute(JOHN_DOLLAR, CHARLIE_AND_THE_CHEWIE_HUMANS, BLOCO_TOTIOQUE, MC_ARIANNE, DECIMAIS_MCS)
    }

    companion object {
        const val JOHN_DOLLAR = "909253"
        const val CHARLIE_AND_THE_CHEWIE_HUMANS = "1171421960"
        const val BLOCO_TOTIOQUE = "358714030"
        const val MC_ARIANNE = "1419227"
        const val DECIMAIS_MCS = "264111789"
    }
}