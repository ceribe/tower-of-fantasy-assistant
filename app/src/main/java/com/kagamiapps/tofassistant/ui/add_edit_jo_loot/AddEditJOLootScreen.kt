package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation
import com.kagamiapps.tofassistant.ui.composables.ComboBox
import com.kagamiapps.tofassistant.util.UiEvent

@Composable
fun AddEditJOLootScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditJOLootViewModel = hiltViewModel()
) {
    val bgColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {}
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditJOLootEvent.OnSaveJOLootClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .background(bgColor)
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            ) {
                JODifficulty.values().forEach {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        RadioButton(
                            onClick = { viewModel.onEvent(AddEditJOLootEvent.OnDifficultyChange(it)) },
                            selected = it == viewModel.difficulty
                        )
                        Text(
                            text = it.level.toString(),
                        )
                    }
                }
            }
            GrayLine()
            ComboBox(
                label = "Joint Operation",
                testTag = "JOINT_OPERATION_COMBOBOX",
                value = viewModel.jo.instanceName,
                suggestions = JointOperation.values().map { it.instanceName },
                editable = false,
                onChangeCallback = {
                    viewModel.onEvent(
                        AddEditJOLootEvent.OnJOChange(
                            JointOperation.getByName(it)
                        )
                    )
                }
            )
            ComboBox(
                label = "Chest Number",
                testTag = "CHEST_NO_COMBOBOX",
                value = viewModel.chestNo.toString(),
                suggestions = (1..3).map { it.toString() },
                editable = false,
                onChangeCallback = {
                    viewModel.onEvent(
                        AddEditJOLootEvent.OnChestNoChange(
                            it.toInt()
                        )
                    )
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .height(56.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Was Chip Used?")
                Switch(
                    checked = viewModel.isChipUsed,
                    onCheckedChange = {
                        viewModel.onEvent(AddEditJOLootEvent.OnIsChipUsedChange(it))
                    }
                )
            }
            GrayLine()
            // TODO Drops
        }
    }
}

@Composable
fun GrayLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.42f))
    ) {}
}