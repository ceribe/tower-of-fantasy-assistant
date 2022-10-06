package com.kagamiapps.tofassistant.ui.jo_loot_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.consts.DropType

@Composable
fun JOLootItem(
    loot: JOLoot,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(1.dp)
    ) {
        Row(
           modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${loot.jo.instanceName} ${loot.difficulty}")
            Row {
                DropType.values().forEach { dropType ->
                    SmallColoredCircle(
                        isDouble = loot.drops.filter { it.type == dropType }.size >= 2,
                        color = if (loot.drops.any { it.type == dropType })
                            dropType.color else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun SmallColoredCircle(
    isDouble: Boolean,
    color: Color,
) {
    Icon(
        imageVector = if (isDouble) Icons.Filled.RadioButtonChecked else Icons.Filled.Circle,
        tint = color,
        contentDescription = ""
    )
}