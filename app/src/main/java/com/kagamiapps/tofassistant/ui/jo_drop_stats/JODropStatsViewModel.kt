package com.kagamiapps.tofassistant.ui.jo_drop_stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JODropStatsViewModel @Inject constructor(
    repository: JOLootRepository
): ViewModel() {

    val loots = repository.getLoots()

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