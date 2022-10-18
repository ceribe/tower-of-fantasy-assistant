package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation
import com.kagamiapps.tofassistant.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEditJOLootViewModel @Inject constructor(
    private val repository: JOLootRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var id by mutableStateOf(-1)
        private set

    var drops by mutableStateOf(emptyList<Drop>())
        private set

    var isChipUsed by mutableStateOf(true)
        private set

    var difficulty by mutableStateOf(JODifficulty.VIII)
        private set

    var jo by mutableStateOf(JointOperation.DeepseaStronghold)
        private set

    var chestNo by mutableStateOf(3)
        private set

    var isNew by mutableStateOf(true)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id = savedStateHandle.get<Int>("id")!!
        isNew = id == -1

        viewModelScope.launch {
            if (id == -1) {
                repository.getLastLoot()?.let {
                    isChipUsed = it.isChipUsed
                    difficulty = it.difficulty
                    jo = it.jo
                    chestNo = it.chestNo
                }
                when (LocalDate.now().dayOfWeek) {
                    DayOfWeek.MONDAY -> jo = JointOperation.QuarantineArea
                    DayOfWeek.TUESDAY -> jo = JointOperation.DeepseaStronghold
                    DayOfWeek.WEDNESDAY -> jo = JointOperation.HyenasArena
                    DayOfWeek.THURSDAY -> jo = JointOperation.DeepseaProvingGround
                    DayOfWeek.FRIDAY -> jo = JointOperation.SpacetimeTrainingGround
                    else -> {}
                }
            }
            else {
                repository.getLootById(id)?.let { loot ->
                    drops = loot.drops
                    isChipUsed = loot.isChipUsed
                    difficulty = loot.difficulty
                    jo = loot.jo
                    chestNo = loot.chestNo
                }
            }
        }
    }

    fun onEvent(event: AddEditJOLootEvent) {
        when(event) {
            is AddEditJOLootEvent.OnChestNoChange -> {
                chestNo = event.chestNo
            }
            is AddEditJOLootEvent.OnDifficultyChange -> {
                difficulty = event.difficulty
            }
            is AddEditJOLootEvent.OnDropsChange -> {
                drops = event.drops
            }
            is AddEditJOLootEvent.OnIsChipUsedChange -> {
                isChipUsed = event.isChipUsed
            }
            is AddEditJOLootEvent.OnJOChange -> {
                jo = event.jo
            }
            is AddEditJOLootEvent.OnSaveJOLootClick -> {
                viewModelScope.launch {
                    repository.upsertLoot(
                        JOLoot(
                            drops = drops.sorted().sortedBy { it.type },
                            isChipUsed = isChipUsed,
                            difficulty = difficulty,
                            jo = jo,
                            chestNo = chestNo,
                            id = if (id == -1) null else id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is AddEditJOLootEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    repository.deleteLootById(id)
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is AddEditJOLootEvent.OnAddNewDrop -> {
                drops = drops + event.drop
            }
            is AddEditJOLootEvent.OnDeleteDrop -> {
                drops = drops.filterIndexed { index, _ -> index != event.index }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}