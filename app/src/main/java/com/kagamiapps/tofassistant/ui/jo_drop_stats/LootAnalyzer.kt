package com.kagamiapps.tofassistant.ui.jo_drop_stats

import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.consts.DropType

object LootAnalyzer {

    fun calculateNumberOfChestsSinceLastDropOfType(loots: List<JOLoot>): Map<DropType, Int> =
        DropType.values().associateWith { dropType ->
            val idx = loots.indexOfFirst { loot ->
                loot.drops.any { drop -> drop.type == dropType }
            }
            if (idx == -1) loots.size else idx
        }

    fun calculateTotalDropsOfType(loots: List<JOLoot>): Map<DropType, Int> =
        DropType.values().associateWith { dropType ->
            loots.sumOf { loot -> loot.drops.count { it.type == dropType } }
        }

    fun calculateAverageNumberOfDropsOfType(loots: List<JOLoot>): Map<DropType, Float> =
        DropType.values().associateWith { dropType ->
            loots.sumOf { loot -> loot.drops.count { it.type == dropType } }.toFloat() / loots.size
        }

    fun calculateLongestStreakWithoutDropOfType(loots: List<JOLoot>): Map<DropType, Int> =
        DropType.values().associateWith { dropType ->
            loots.fold(0 to 0) { (consecutiveMax, curr), joLoot ->
                val doesContain = joLoot.drops.any { it.type == dropType }
                if (doesContain)
                    consecutiveMax to 0
                else
                    Integer.max(consecutiveMax, curr + 1) to curr + 1
            }.first
        }
}