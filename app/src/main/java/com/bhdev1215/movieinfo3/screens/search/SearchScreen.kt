package com.bhdev1215.movieinfo3.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bhdev1215.movieinfo3.screens.components.CommonAppBar
import com.bhdev1215.movieinfo3.ui.theme.primaryGray
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResult = viewModel.searchResult.value.collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize()) {
        CommonAppBar(
            title = {
                Text(text = "검색", color = Color.White, fontSize = 18.sp)
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
            navController = navController,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState
        )

        SearchTextField(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(4.dp),
            onSearch = {
                viewModel.getSearchResult(it)
                keyboardController?.hide()
            }
        )

        Box(Modifier.fillMaxSize()) {
            LazyColumn {
                items(searchResult.itemCount) { search ->
                    Timber.d("searchResult : ${searchResult[search]?.title}")
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val resetSearchTerm = viewModel.resetSearchTerm.value

    TextField(
        value = resetSearchTerm,
        onValueChange = {
            viewModel.resetSearchTerm(it)
        },
        placeholder = {
            Text(text = "검색어를 입력하세요", color = primaryGray)

        },
        modifier = modifier
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { onSearch(resetSearchTerm) }) {
                Icon(imageVector = Icons.Default.Search, tint = primaryGray, contentDescription = null)
            }
        }
    )
}