package com.bhdev1215.movieinfo3.screens.components.drawer

import android.util.Log
import androidx.compose.runtime.Composable
import com.bhdev1215.movieinfo3.model.NavigationDrawerItem
import timber.log.Timber

@Composable
fun DrawerBody(
    item: NavigationDrawerItem,
    itemClick: (String) -> Unit,
) {
    NavigationMenuItem(item = item) {
        Timber.d("item : $item")
        itemClick(item.title)
    }
}