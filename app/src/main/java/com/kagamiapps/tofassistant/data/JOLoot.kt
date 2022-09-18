package com.kagamiapps.tofassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation

@Entity
data class JOLoot(
    val drops: List<Drop>,
    val isChipUsed: Boolean,
    val difficulty: JODifficulty,
    val jo: JointOperation,
    val chestNo: Int,
    @PrimaryKey val id: Int? = null
)
