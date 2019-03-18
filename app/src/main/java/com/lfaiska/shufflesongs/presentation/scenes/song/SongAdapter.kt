package com.lfaiska.shufflesongs.presentation.scenes.song

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfaiska.shufflesongs.R
import com.lfaiska.shufflesongs.databinding.ItemListSongBinding
import com.lfaiska.shufflesongs.domain.Song

class SongAdapter(private val songList: List<Song>) : RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_song, parent, false))
    }

    override fun getItemCount() = songList.count()

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val binding = holder.binding
        binding?.let {
            it.viewModel = createViewModel(songList[position])
            it.executePendingBindings()
        }
    }

    private fun createViewModel(song: Song) =
            with(song) {
                SongViewModel(artWork, artist.name, name, genre)
            }
}

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding: ItemListSongBinding? = DataBindingUtil.bind(view)
}