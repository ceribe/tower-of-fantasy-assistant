package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.consts.Region
import com.kagamiapps.tofassistant.ui.jo_loot_list.JOLootListEvent
import com.kagamiapps.tofassistant.util.Routes
import com.kagamiapps.tofassistant.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class JODropStatsViewModel @Inject constructor(
    repository: JOLootRepository
): ViewModel() {

    private val loots = repository.getLoots().map { it.asReversed() }

    val region = MutableStateFlow(Region.Aesperia)

    private val filteredLoots = combine(loots, region) { loots, region ->
        loots.filter { it.jo.region == region }
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
                region.value = event.region
            }
        }
    }
}