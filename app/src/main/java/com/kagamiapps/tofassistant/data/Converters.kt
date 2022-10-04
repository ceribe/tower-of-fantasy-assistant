package com.kagamiapps.tofassistant.data

import androidx.room.TypeConverter
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation

object Converters {
    @TypeConverter
    fun fromDropList(drops: List<Drop>): String {
        return drops.map { it.id }.joinToString(",")
    }

    @TypeConverter
    fun toDropList(drops: String): List<Drop> {
        if (drops == "")
            return emptyList()
        return drops.split(",").map { Drop.getById(it.toInt()) }
    }

    @TypeConverter
    fun fromJODifficulty(difficulty: JODifficulty): Int {
        return difficulty.level
    }

    @TypeConverter
    fun toJODifficulty(level: Int): JODifficulty {
        return JODifficulty.getByLevel(level)
    }

    @TypeConverter
    fun fromJointOperation(jo: JointOperation): Int {
        return jo.id
    }

    @TypeConverter
    fun toJointOperation(id: Int): JointOperation {
        return JointOperation.getById(id)
    }
}