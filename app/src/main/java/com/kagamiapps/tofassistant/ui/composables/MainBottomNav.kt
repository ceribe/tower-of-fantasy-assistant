package com.kagamiapps.tofassistant.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.kagamiapps.tofassistant.util.Routes
import com.kagamiapps.tofassistant.util.UiEvent

data class BottomRoute(val route: String, val icon: ImageVector, val text: String)

val bottomRoutes = listOf(
    BottomRoute(Routes.JO_LOOT_LIST, Icons.Default.List, "JO Drops"),
    BottomRoute(Routes.JO_DROP_STATS, Icons.Default.QueryStats, "JO Stats")
)

@Composable
fun MainBottomNav(
    onNavigate: (UiEvent.Navigate) -> Unit,
    routes: List<BottomRoute>,
    selectedRoute: String
) {
    BottomNavigation {
        routes.forEach { (route, icon, text) ->
            BottomNavigationItem(
                selected = selectedRoute == route,
                onClick = { onNavigate(UiEvent.Navigate(route)) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = text
                    )
                },
                label = { Text(text = text) }
            )
        }
    }
}