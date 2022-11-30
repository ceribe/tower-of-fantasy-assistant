package com.kagamiapps.tofassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kagamiapps.tofassistant.ui.add_edit_jo_loot.AddEditJOLootScreen
import com.kagamiapps.tofassistant.ui.crit_comparer.CritComparerScreen
import com.kagamiapps.tofassistant.ui.jo_drop_stats.JODropStatsScreen
import com.kagamiapps.tofassistant.ui.jo_loot_list.JOLootListScreen
import com.kagamiapps.tofassistant.ui.theme.TowerOfFantasyAssistantTheme
import com.kagamiapps.tofassistant.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TowerOfFantasyAssistantTheme {
                val navController = rememberNavController()
                Scaffold { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.JO_LOOT_LIST
                        ) {
                            composable(
                                route = Routes.JO_LOOT_LIST
                            ) {
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

                            composable(
                                route = Routes.JO_DROP_STATS
                            ) {
                                JODropStatsScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }

                            composable(
                                route = Routes.CRIT_COMPARER
                            ) {
                                CritComparerScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
