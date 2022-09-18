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

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id = savedStateHandle.get<Int>("id")!!
        if (id != -1) {
            viewModelScope.launch {
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
            AddEditJOLootEvent.OnSaveJOLootClick -> {
                viewModelScope.launch {
                    repository.upsertLoot(
                        JOLoot(
                            drops = drops,
                            isChipUsed = isChipUsed,
                            difficulty = difficulty,
                            jo = jo,
                            chestNo = chestNo,
                            id = id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}