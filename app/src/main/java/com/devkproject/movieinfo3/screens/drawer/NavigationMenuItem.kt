package com.devkproject.movieinfo3.screens.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devkproject.movieinfo3.model.NavigationDrawerItem
import com.devkproject.movieinfo3.ui.theme.quicksand

@Composable
fun NavigationMenuItem(
    item: NavigationDrawerItem,
    itemClick: () -> Unit
) {
    val texColor = if (item.isSelected) Color.Black else Color.White
    val backgroundColor = if (item.isSelected) Color.White else Color.Transparent

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .clickable { itemClick() }
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = item.image,
                contentDescription = null,
                tint = texColor
            )
            Text(
                text = item.titleFormat(),
                fontSize = 18.sp,
                fontFamily = quicksand,
                fontWeight = FontWeight.Normal,
                color = texColor,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}