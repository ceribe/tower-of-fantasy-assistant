package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.consts.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class JODropStatsViewModel @Inject constructor(
    repository: JOLootRepository
): ViewModel() {

    private val loots = repository.getLoots().map { it.asReversed() }

    private val _region = MutableStateFlow<Region?>(null)
    val region: StateFlow<Region?> = _region

    private val filteredLoots = combine(loots, region) { loots, region ->
        loots.filter { region == null || it.jo.region == region }
    }

    val numberOfChestsSinceLastDropOfType = filteredLoots
        .map(LootAnalyzer::calculateNumberOfChestsSinceLastDropOfType)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val totalDropsOfType = filteredLoots
        .map(LootAnalyzer::calculateTotalDropsOfType)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val averageNumberOfDropsOfType = filteredLoots
        .map(LootAnalyzer::calculateAverageNumberOfDropsOfType)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    val longestStreakWithoutDropOfType = filteredLoots
        .map(LootAnalyzer::calculateLongestStreakWithoutDropOfType)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())


    fun onEvent(event: JODropStatsEvent) {
        when(event) {
            is JODropStatsEvent.OnRegionChange -> {
                _region.value = event.region
            }
        }
    }
}