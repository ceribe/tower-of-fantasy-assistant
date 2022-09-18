package com.kagamiapps.tofassistant.ui.jo_loot_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kagamiapps.tofassistant.data.JOLoot

@Composable
fun JOLootItem(
    loot: JOLoot,
    onEvent: (JOLootListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text = loot.jo.name)
}