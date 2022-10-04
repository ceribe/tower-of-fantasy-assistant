package com.kagamiapps.tofassistant.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kagamiapps.tofassistant.util.BottomRoute
import com.kagamiapps.tofassistant.util.UiEvent
import com.kagamiapps.tofassistant.util.bottomRoutes

@Composable
fun MainBottomNav(
    onNavigate: (UiEvent.Navigate) -> Unit,
    selectedRoute: String,
    routes: List<BottomRoute> = bottomRoutes,
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