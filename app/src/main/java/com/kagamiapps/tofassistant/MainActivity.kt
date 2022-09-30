package com.kagamiapps.tofassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kagamiapps.tofassistant.ui.add_edit_jo_loot.AddEditJOLootScreen
import com.kagamiapps.tofassistant.ui.jo_drop_stats.JODropStatsScreen
import com.kagamiapps.tofassistant.ui.jo_loot_list.JOLootListScreen
import com.kagamiapps.tofassistant.ui.theme.TowerOfFantasyAssistantTheme
import com.kagamiapps.tofassistant.util.Routes
import dagger.hilt.android.AndroidEntryPoint

data class BottomRoute(val route: String, val icon: ImageVector, val text: String)

val bottomRoutes = listOf(
    BottomRoute(Routes.JO_LOOT_LIST, Icons.Default.List, "JO Drops"),
    BottomRoute(Routes.JO_DROP_STATS, Icons.Default.QueryStats, "JO Stats")
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TowerOfFantasyAssistantTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            bottomRoutes.forEach { (route, icon, text) ->
                                BottomNavigationItem(
                                    selected = navBackStackEntry?.destination?.route == route,
                                    onClick = { navController.navigate(route) },
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
                ) { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Routes.JO_LOOT_LIST
                        ) {
                            composable(Routes.JO_LOOT_LIST) {
                                JOLootListScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }
                            composable(
                                route = Routes.ADD_EDIT_JO_LOOT + "?id={id}",
                                arguments = listOf(
                                    navArgument(name = "id") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddEditJOLootScreen(onPopBackStack = {
                                    navController.popBackStack()
                                })
                            }
                            composable(Routes.JO_DROP_STATS) {
                                JODropStatsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
