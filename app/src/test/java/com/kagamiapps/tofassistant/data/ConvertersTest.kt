package com.kagamiapps.tofassistant.data

import com.kagamiapps.tofassistant.data.consts.Drop
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {
    @Test
    fun should_convert_empty_drop_list() {
        val emptyDropList = emptyList<Drop>()
        val convertedEmptyDropList = Converters.fromDropList(emptyDropList)
        val twiceConvertedEmptyDropList = Converters.toDropList(convertedEmptyDropList)
        assertEquals(0, twiceConvertedEmptyDropList.size)
    }

    @Test
    fun should_convert_non_empty_drop_list() {
        val dropList = listOf(Drop.EchoMatrix, Drop.SSRArmor, Drop.CrowMatrix)
        val convertedDropList = Converters.fromDropList(dropList)
        val twiceConvertedDropList = Converters.toDropList(convertedDropList)

        assertEquals(dropList.size, twiceConvertedDropList.size)
        for (i in dropList.indices) {
            assertEquals(dropList[i], twiceConvertedDropList[i])
        }
    }
}