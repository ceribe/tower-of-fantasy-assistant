package com.kagamiapps.tofassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation

@Entity
data class JOLoot(
    val drops: List<Drop>,
    val isChipUsed: Boolean = true,
    val difficulty: JODifficulty = JODifficulty.L70,
    val jo: JointOperation = JointOperation.QuarantineArea,
    val chestNo: Int = 3,
    @PrimaryKey val id: Int? = null
)
