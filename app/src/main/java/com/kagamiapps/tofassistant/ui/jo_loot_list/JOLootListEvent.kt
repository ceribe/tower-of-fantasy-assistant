package com.kagamiapps.tofassistant.ui.jo_loot_list

import com.kagamiapps.tofassistant.data.JOLoot

sealed class JOLootListEvent {
    data class OnLootClick(val loot: JOLoot): JOLootListEvent()
    object OnCreateLootClick: JOLootListEvent()
}
