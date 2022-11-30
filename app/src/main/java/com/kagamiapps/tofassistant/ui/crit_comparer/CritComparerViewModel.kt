package com.kagamiapps.tofassistant.ui.crit_comparer

import androidx.lifecycle.ViewModel
import com.kagamiapps.tofassistant.ui.jo_drop_stats.JODropStatsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class CritComparerViewModel @Inject constructor(
): ViewModel() {

    private var _level = MutableStateFlow(80)
    val level: StateFlow<Int> = _level

    private var _atk1 = MutableStateFlow(0)
    val atk1: StateFlow<Int> = _atk1

    private var _atk2 = MutableStateFlow(0)
    val atk2: StateFlow<Int> = _atk2

    private var _crit1 = MutableStateFlow(0)
    val crit1: StateFlow<Int> = _crit1

    private var _crit2 = MutableStateFlow(0)
    val crit2: StateFlow<Int> = _crit2

    val score1 = combine(atk1, crit1, level, GearScoreCalculator::calculateGearScore)
    val score2 = combine(atk2, crit2, level, GearScoreCalculator::calculateGearScore)

    val critRate1 = combine(crit1, level, GearScoreCalculator::calculateCritRate)
    val critRate2 = combine(crit2, level, GearScoreCalculator::calculateCritRate)

    fun onEvent(event: CritComparerEvent) {
        when(event) {
            is CritComparerEvent.OnLevelChange -> _level.value = event.level
            is CritComparerEvent.OnAtkChange ->
                if (event.id == "A") _atk1.value = event.atk else _atk2.value = event.atk
            is CritComparerEvent.OnCritChange ->
                if (event.id == "A") _crit1.value = event.crit else _crit2.value = event.crit
        }
    }
}