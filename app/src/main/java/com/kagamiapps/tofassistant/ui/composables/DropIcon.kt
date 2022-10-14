package com.kagamiapps.tofassistant.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DropIcon(dropImageId: Int) {
    Image(
        painter = painterResource(id = dropImageId),
        contentDescription = "",
        modifier = Modifier
            .height(56.dp),
        contentScale = ContentScale.FillHeight
    )
}