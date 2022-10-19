package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation

sealed class AddEditJOLootEvent {
    data class OnDropsChange(val drops: List<Drop>): AddEditJOLootEvent()
    data class OnIsChipUsedChange(val isChipUsed: Boolean): AddEditJOLootEvent()
    data class OnDifficultyChange(val difficulty: JODifficulty): AddEditJOLootEvent()
    data class OnJOChange(val jo: JointOperation): AddEditJOLootEvent()
    data class OnChestNoChange(val chestNo: Int): AddEditJOLootEvent()
    object OnSaveJOLootClick: AddEditJOLootEvent()
    object OnDeleteClick: AddEditJOLootEvent()
    data class OnAddNewDrop(val drop: Drop): AddEditJOLootEvent()
    data class OnDeleteDrop(val index: Int): AddEditJOLootEvent()
}
