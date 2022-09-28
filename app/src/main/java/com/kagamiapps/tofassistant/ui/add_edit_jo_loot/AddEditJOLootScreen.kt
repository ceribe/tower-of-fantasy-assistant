package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.data.consts.Drop
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
        topBar = {
                 TopAppBar(
                     title = { Text(text = "Input data") },
                     actions = {
                         IconButton(
                             onClick = {
                                 viewModel.onEvent(AddEditJOLootEvent.OnDeleteClick)
                             }
                         ) {
                             Icon(
                                 imageVector = Icons.Default.Delete,
                                 contentDescription = "Delete"
                             )
                         }
                     }
                 )
        },
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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            GrayLine()
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
            Column(
                modifier = Modifier
                    .background(bgColor)
            ) {
                viewModel.drops.forEachIndexed { index, drop ->
                    EditableDrop(drop, index, viewModel::onEvent)
                    GrayLine()
                }
            }
            ComboBox(
                label = "Add Drop",
                testTag = "ADD_DROP_COMBOBOX",
                value = "",
                suggestions = viewModel.jo.getAllDrops().map { it.itemName },
                editable = false,
                onChangeCallback = {
                    viewModel.onEvent(
                        AddEditJOLootEvent.OnAddNewDrop(
                            Drop.getByName(it)
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(100.dp))
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

@Composable
fun EditableDrop(
    drop: Drop,
    index: Int,
    onEvent: (AddEditJOLootEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = drop.itemName)
        IconButton(onClick = { onEvent(AddEditJOLootEvent.OnDeleteDrop(index)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Drop"
            )
        }
    }
}