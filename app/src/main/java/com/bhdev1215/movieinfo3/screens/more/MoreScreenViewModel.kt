package com.bhdev1215.movieinfo3.screens.more

import androidx.lifecycle.ViewModel
import com.bhdev1215.movieinfo3.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}