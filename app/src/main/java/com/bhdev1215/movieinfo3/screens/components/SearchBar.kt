package com.bhdev1215.movieinfo3.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import com.bhdev1215.movieinfo3.screens.home.HomeViewModel

@Composable
fun SearchBar(viewModel: HomeViewModel) {
    var text by remember { mutableStateOf("") }
    Column {
        TextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.getSearch(it)
            }
        )
    }
}