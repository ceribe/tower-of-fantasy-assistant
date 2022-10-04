package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.consts.DropType
import com.kagamiapps.tofassistant.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Integer.max
import javax.inject.Inject

@HiltViewModel
class JODropStatsViewModel @Inject constructor(
    repository: JOLootRepository
): ViewModel() {

    private val loots = repository.getLoots().map { it.asReversed() }

    val numberOfChestsSinceLastDropOfType = loots.map { loots ->
        DropType.values().associateWith { dropType ->
            loots.indexOfFirst {
                    loot -> loot.drops.any { drop -> drop.type == dropType }
            }.coerceAtLeast(0)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val totalDropsOfType = loots.map { loots ->
        DropType.values().associateWith { dropType ->
            loots.sumOf { loot -> loot.drops.count { it.type == dropType } }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val averageNumberOfDropsOfType = loots.map { loots ->
        DropType.values().associateWith { dropType ->
            loots.sumOf { loot -> loot.drops.count { it.type == dropType } }.toFloat() / loots.size
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val longestStreakWithoutDropOfType = loots.map { loots ->
        DropType.values().associateWith { dropType ->
            loots.fold(0 to 0) { (consecutiveMax, curr), joLoot ->
                val doesContain = joLoot.drops.any { it.type == dropType }
                if (doesContain)
                    consecutiveMax to 0
                else
                    max(consecutiveMax, curr + 1) to curr + 1
            }.first
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: JODropStatsEvent) {

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}