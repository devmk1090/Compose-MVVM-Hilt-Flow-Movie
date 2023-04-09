package com.devkproject.movieinfo3.screens.more

import androidx.lifecycle.ViewModel
import com.devkproject.movieinfo3.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}