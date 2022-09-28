package com.kagamiapps.tofassistant.ui.jo_loot_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.consts.DropType

@Composable
fun JOLootItem(
    loot: JOLoot,
    onEvent: (JOLootListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val purple = Color(0xffbb47e1)
    val orange = Color(0xffe58631)
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
                SmallColoredCircle(
                    color = if (loot.drops.any { it.type == DropType.SREquipment })
                        purple else Color.Gray
                )
                SmallColoredCircle(
                    color = if (loot.drops.any { it.type == DropType.SSREquipment })
                        orange else Color.Gray
                )
                SmallColoredCircle(
                    color = if (loot.drops.any { it.type == DropType.SRMatrix })
                        purple else Color.Gray
                )
                SmallColoredCircle(
                    color = if (loot.drops.any { it.type == DropType.SSRMatrix })
                        orange else Color.Gray
                )
            }
        }
    }
}

@Composable
fun SmallColoredCircle(
    color: Color
) {
    Icon(
        imageVector = Icons.Filled.Circle,
        tint = color,
        contentDescription = ""
    )
}