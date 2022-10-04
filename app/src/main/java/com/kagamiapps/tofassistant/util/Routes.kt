package com.kagamiapps.tofassistant.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.ui.graphics.vector.ImageVector

object Routes {
    const val JO_LOOT_LIST = "jo_loot_list"
    const val ADD_EDIT_JO_LOOT = "add_edit_jo_loot"
    const val JO_DROP_STATS = "jo_drop_stats"
}

data class BottomRoute(val route: String, val icon: ImageVector, val text: String)

val bottomRoutes = listOf(
    BottomRoute(Routes.JO_LOOT_LIST, Icons.Default.List, "JO Drops"),
    BottomRoute(Routes.JO_DROP_STATS, Icons.Default.QueryStats, "JO Stats")
)