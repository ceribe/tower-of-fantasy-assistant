package com.kagamiapps.tofassistant.ui.crit_comparer

import java.math.RoundingMode

object GearScoreCalculator {
    fun calculateGearScore(atk: Int, crit: Int, level: Int): Float {
        val critRate = (calculateCritRate(crit, level) / 100).round(2)
        return ((atk * critRate * 1.5f + atk * (1 - critRate)) / 1000).round(3)
    }

    fun calculateCritRate(crit: Int, level: Int): Float {
        return (crit / (level * 2.66f - 27)).round(2)
    }
}

fun Float.round(decimals: Int) = this
    .toBigDecimal()
    .setScale(decimals, RoundingMode.HALF_UP)
    .toFloat()