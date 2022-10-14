package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLootRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class JODropStatsViewModel @Inject constructor(
    repository: JOLootRepository
): ViewModel() {

    private val loots = repository.getLoots().map { it.asReversed() }

    val numberOfChestsSinceLastDropOfType = loots.map { loots ->
        LootAnalyzer.calculateNumberOfChestsSinceLastDropOfType(loots)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val totalDropsOfType = loots.map { loots ->
        LootAnalyzer.calculateTotalDropsOfType(loots)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val averageNumberOfDropsOfType = loots.map { loots ->
        LootAnalyzer.calculateAverageNumberOfDropsOfType(loots)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val longestStreakWithoutDropOfType = loots.map { loots ->
        LootAnalyzer.calculateLongestStreakWithoutDropOfType(loots)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())
}