package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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

    Column {
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
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Average")
                            Text(text = "%.4f".format(averageNumberOfDropsOfType[dropType]))
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Chests since last", textAlign = TextAlign.Center)
                            Text(text = numberOfChestsSinceLastDropOfType[dropType].toString())
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Total")
                            Text(text = totalDropsOfType[dropType].toString())
                        }
                    }
                }
            }
        }
    }
}