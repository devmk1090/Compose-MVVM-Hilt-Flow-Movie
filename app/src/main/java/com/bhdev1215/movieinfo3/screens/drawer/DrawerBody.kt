package com.bhdev1215.movieinfo3.screens.drawer

import androidx.compose.runtime.Composable
import com.bhdev1215.movieinfo3.model.NavigationDrawerItem

@Composable
fun DrawerBody(
    item: NavigationDrawerItem,
    itemClick: (String) -> Unit,
) {
    NavigationMenuItem(item = item) {
        itemClick(item.title)
    }
}