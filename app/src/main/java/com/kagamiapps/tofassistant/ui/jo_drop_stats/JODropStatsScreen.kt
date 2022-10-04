package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.data.consts.DropType
import com.kagamiapps.tofassistant.ui.composables.MainBottomNav
import com.kagamiapps.tofassistant.util.Routes
import com.kagamiapps.tofassistant.util.UiEvent

@Composable
fun JODropStatsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: JODropStatsViewModel = hiltViewModel()
) {
    val numberOfChestsSinceLastDropOfType by
        viewModel.numberOfChestsSinceLastDropOfType.collectAsState(initial = emptyMap())

    val totalDropsOfType by
        viewModel.totalDropsOfType.collectAsState(initial = emptyMap())

    val averageNumberOfDropsOfType by
        viewModel.averageNumberOfDropsOfType.collectAsState(initial = emptyMap())

    val longestStreakWithoutDropOfType by
        viewModel.longestStreakWithoutDropOfType.collectAsState(initial = emptyMap())

    Scaffold(
        bottomBar = { MainBottomNav(onNavigate = onNavigate, selectedRoute = Routes.JO_DROP_STATS) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            DropType.values().forEach { dropType ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dropType.typeName,
                            color = dropType.color
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Average")
                                Text(text = "%.3f".format(averageNumberOfDropsOfType[dropType]))
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Streak w/o drop", textAlign = TextAlign.Center)
                                Text(text = numberOfChestsSinceLastDropOfType[dropType].toString())
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Total")
                                Text(text = totalDropsOfType[dropType].toString())
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 2.dp)
                        ) {
                            Text(text = "Longest streak w/o drop")
                            Text(text = longestStreakWithoutDropOfType[dropType].toString())
                        }
                    }
                }
            }
        }
    }
}