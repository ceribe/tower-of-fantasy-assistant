package com.kagamiapps.tofassistant.ui.crit_comparer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.ui.composables.MainBottomNav
import com.kagamiapps.tofassistant.util.Routes
import com.kagamiapps.tofassistant.util.UiEvent

@Composable
fun CritComparerScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: CritComparerViewModel = hiltViewModel()
) {
    val level by viewModel.level.collectAsState()
    val atk1 by viewModel.atk1.collectAsState()
    val atk2 by viewModel.atk2.collectAsState()
    val crit1 by viewModel.crit1.collectAsState()
    val crit2 by viewModel.crit2.collectAsState()
    val score1 by viewModel.score1.collectAsState(0.0f)
    val score2 by viewModel.score2.collectAsState(0.0f)
    val critRate1 by viewModel.critRate1.collectAsState(0.0f)
    val critRate2 by viewModel.critRate2.collectAsState(0.0f)

    Scaffold(
        bottomBar = {
            MainBottomNav(
                onNavigate = onNavigate,
                selectedRoute = Routes.CRIT_COMPARER
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12f))
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Input the wanderer's level and stats. In the left column input total stats with gear piece A and in the right column with gear piece B."
                )
            }
            TextField(
                value = level.toString(),
                label = { Text(text = "Wanderer's Level") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Row {
                InputColumn(atk1, crit1, viewModel::onEvent, "A")
                InputColumn(atk2, crit2, viewModel::onEvent, "B")
            }
            StatsColumn(score1, score2, critRate1, critRate2)
        }
    }
}

@Composable
fun RowScope.InputColumn(atk: Int, crit: Int, onEvent: (CritComparerEvent) -> Unit, id: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(1.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // TODO: At some point this could be extracted to a separate component
        TextField(
            value = if (atk == 0) "" else atk.toString(),
            label = { Text(text = "Attack with piece $id") },
            onValueChange = {
                if (it == "") {
                    onEvent(CritComparerEvent.OnAtkChange(0, id))
                    return@TextField
                }

                val number = it.toIntOrNull()
                if (number != null)
                    onEvent(CritComparerEvent.OnAtkChange(number, id))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = if (crit == 0) "" else crit.toString(),
            label = { Text(text = "Crit with piece $id") },
            onValueChange = {
                if (it == "") {
                    onEvent(CritComparerEvent.OnCritChange(0, id))
                    return@TextField
                }

                val number = it.toIntOrNull()
                if (number != null)
                    onEvent(CritComparerEvent.OnCritChange(number, id))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun StatsColumn(score1: Float, score2: Float, critRate1: Float, critRate2: Float) {
    Card(
        modifier = Modifier.padding(2.dp),
        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Crit Rate", fontSize = 28.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatText(critRate1, "%")
                StatText(critRate2, "%")
            }
            Text(text = "Gear Score", fontSize = 28.sp, modifier = Modifier.padding(top = 32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatText(score1)
                StatText(score2)
            }
            Text(text = "Gear score includes crit rate.", modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun StatText(value: Float, suffix: String = "") {
    Text(
        text = value.toString() + suffix,
        fontSize = 20.sp,
        modifier = Modifier.width(100.dp),
        textAlign = TextAlign.Center
    )
}