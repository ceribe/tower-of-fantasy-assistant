package com.kagamiapps.tofassistant.ui.jo_drop_stats

import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.DropType
import org.junit.Assert.assertEquals
import org.junit.Test

internal class LootAnalyzerTest {
    private val loots = listOf(
        JOLoot(drops = listOf()),
        JOLoot(drops = listOf(Drop.SRSuit, Drop.SRBoots, Drop.SRArmbands)),
        JOLoot(drops = listOf()),
        JOLoot(drops = listOf(Drop.SRHelmet)),
        JOLoot(drops = listOf(Drop.EchoMatrix)),
        JOLoot(drops = listOf(Drop.SSRArmor, Drop.SSRSabatons)),
        JOLoot(drops = listOf()),
        JOLoot(drops = listOf()),
        JOLoot(drops = listOf(Drop.HildaMatrix))
    )

    @Test
    fun should_calculate_number_of_chests_since_last_drop_of_type() {
        with(LootAnalyzer.calculateNumberOfChestsSinceLastDropOfType(loots)) {
            assertEquals(1, this[DropType.SREquipment])
            assertEquals(5, this[DropType.SSREquipment])
            assertEquals(4, this[DropType.SRMatrix])
            assertEquals(9, this[DropType.SSRMatrix])
        }
    }

    @Test
    fun should_calculate_total_drops_of_type() {
        with(LootAnalyzer.calculateTotalDropsOfType(loots)) {
            assertEquals(4, this[DropType.SREquipment])
            assertEquals(2, this[DropType.SSREquipment])
            assertEquals(2, this[DropType.SRMatrix])
            assertEquals(0, this[DropType.SSRMatrix])
        }
    }

    @Test
    fun should_calculate_average_number_of_drops_of_type() {
        with(LootAnalyzer.calculateAverageNumberOfDropsOfType(loots)) {
            assertEquals(4.0f/loots.size, this[DropType.SREquipment]!!, 0.01f)
            assertEquals(2.0f/loots.size, this[DropType.SSREquipment]!!, 0.01f)
            assertEquals(2.0f/loots.size, this[DropType.SRMatrix]!!, 0.01f)
            assertEquals(0.0f/loots.size, this[DropType.SSRMatrix]!!, 0.01f)
        }
    }

    @Test
    fun should_calculate_longest_streak_without_drop_of_type() {
        with(LootAnalyzer.calculateLongestStreakWithoutDropOfType(loots)) {
            assertEquals(5, this[DropType.SREquipment])
            assertEquals(5, this[DropType.SSREquipment])
            assertEquals(4, this[DropType.SRMatrix])
            assertEquals(9, this[DropType.SSRMatrix])
        }
    }

    @Test
    fun should_calculate_average_number_of_drops_of_type_when_no_data() {
        with(LootAnalyzer.calculateAverageNumberOfDropsOfType(emptyList())) {
            assertEquals(0.0f, this[DropType.SSRMatrix]!!, 0.01f)
        }
    }
}