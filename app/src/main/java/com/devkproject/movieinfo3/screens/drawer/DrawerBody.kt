package com.devkproject.movieinfo3.screens.drawer

import androidx.compose.runtime.Composable
import com.devkproject.movieinfo3.model.NavigationDrawerItem

@Composable
fun DrawerBody(
    item: NavigationDrawerItem,
    itemClick: (String) -> Unit,
) {
    NavigationMenuItem(item = item) {
        itemClick(item.title)
    }
}