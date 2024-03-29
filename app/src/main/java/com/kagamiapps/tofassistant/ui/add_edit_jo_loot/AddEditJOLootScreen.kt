package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JointOperation
import com.kagamiapps.tofassistant.data.consts.Region
import com.kagamiapps.tofassistant.ui.composables.ComboBox
import com.kagamiapps.tofassistant.ui.composables.DropIcon
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
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {}
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = if (viewModel.isNew) "Add new entry" else "Edit entry") },
                actions = {
                    if (!viewModel.isNew) {
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
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DifficultySelection(bgColor, viewModel)
            JOSelection(viewModel)
            ChestNumberSelection(viewModel)
            ChipUsedSelection(bgColor, viewModel)
            AddDropButton(viewModel)
            DropList(bgColor, viewModel)
        }
    }
}

@Composable
private fun GrayLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.42f))
    ) {}
}

@Composable
private fun DifficultySelection(
    bgColor: Color,
    viewModel: AddEditJOLootViewModel
) {
    val region by viewModel.region.collectAsState(Region.Aesperia)
    GrayLine()
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(bgColor)
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    ) {
        region.joDifficulties.forEach {
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
}

@Composable
private fun JOSelection(viewModel: AddEditJOLootViewModel) {
    val jo by viewModel.jo.collectAsState(JointOperation.DeepseaStronghold)
    ComboBox(
        label = "Joint Operation",
        testTag = "JOINT_OPERATION_COMBOBOX",
        value = jo.instanceName,
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
}

@Composable
private fun ChestNumberSelection(viewModel: AddEditJOLootViewModel) {
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
}

@Composable
private fun ChipUsedSelection(
    bgColor: Color,
    viewModel: AddEditJOLootViewModel
) {
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
}

@Composable
private fun AddDropButton(viewModel: AddEditJOLootViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val jo by viewModel.jo.collectAsState(JointOperation.HyenasArena)
    Button(
        modifier = Modifier
            .height(56.dp)
            .padding(4.dp),
        onClick = { expanded = !expanded },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = "Add drop")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        jo.getAllDrops().forEach { drop ->
            DropdownMenuItem(
                modifier = Modifier.width(250.dp),
                onClick = {
                    expanded = false
                    viewModel.onEvent(
                        AddEditJOLootEvent.OnAddNewDrop(
                            Drop.getByName(drop.itemName)
                        )
                    )
                }
            ) {
                DropIcon(dropImageId = drop.imageId)
                Text(text = drop.itemName, modifier = Modifier.padding(start = 16.dp))
            }
        }
    }
}

@Composable
private fun DropList(
    bgColor: Color,
    viewModel: AddEditJOLootViewModel
) {
    if (viewModel.drops.isNotEmpty())
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
    Spacer(modifier = Modifier.height(100.dp))
}

@Composable
private fun EditableDrop(
    drop: Drop,
    index: Int,
    onEvent: (AddEditJOLootEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropIcon(drop.imageId)
            Text(
                text = drop.itemName,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        IconButton(onClick = { onEvent(AddEditJOLootEvent.OnDeleteDrop(index)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Drop"
            )
        }
    }
}