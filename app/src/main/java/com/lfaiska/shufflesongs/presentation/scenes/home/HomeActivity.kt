package com.lfaiska.shufflesongs.presentation.scenes.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfaiska.shufflesongs.R
import com.lfaiska.shufflesongs.databinding.ActivityHomeBinding
import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.presentation.scenes.song.SongAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.lfaiska.shufflesongs.domain.useCase.PlaylistUseCaseImpl
import android.view.Menu

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupRecyclerView()
        initViewModel()
        observeEvents()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.songList.layoutManager = layoutManager
    }

    private fun initViewModel() {
        viewModel = HomeViewModel(PlaylistUseCaseImpl())
        binding.viewModel = viewModel
        viewModel.loadSongs()
    }

    private fun observeEvents() {
        viewModel.songListMutableLiveData.observe(this, Observer<List<Song>> { songList ->
            populateSongAdapter(songList)
            hideProgress()
        })
    }

    private fun populateSongAdapter(songList: List<Song>?) {
        songList?.let {
            binding.songList.adapter = SongAdapter(it)
        }
    }

    fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_shuffle -> {
            viewModel.onShuffleButtonTouched()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
