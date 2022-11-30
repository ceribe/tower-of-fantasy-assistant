package com.kagamiapps.tofassistant.ui.crit_comparer

sealed class CritComparerEvent {
    data class OnAtkChange(val atk: Int, val id: String): CritComparerEvent()
    data class OnCritChange(val crit: Int, val id: String): CritComparerEvent()
    data class OnLevelChange(val level: Int): CritComparerEvent()
}