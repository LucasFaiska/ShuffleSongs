package com.lfaiska.shufflesongs.presentation.scenes.home

import com.lfaiska.shufflesongs.domain.Song
import com.lfaiska.shufflesongs.domain.useCase.PlayListUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
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
        viewModel = HomeViewModel(playlistUseCase)
    }

    @Test
    fun whenLoadSongs_mustBeCallUseCase() {
        viewModel.loadSongs()
    }

}