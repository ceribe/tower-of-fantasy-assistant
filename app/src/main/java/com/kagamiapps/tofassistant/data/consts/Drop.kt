package com.kagamiapps.tofassistant.data.consts

import androidx.compose.ui.graphics.Color
import com.kagamiapps.tofassistant.ui.theme.OrangeSSR
import com.kagamiapps.tofassistant.ui.theme.PurpleSR

enum class Drop(val id: Int, val itemName: String, val type: DropType) {
    SSRHelm(1, "SSR Helm", DropType.SSREquipment),
    SSRSpaulders(2, "SSR Spaulders", DropType.SSREquipment),
    SSRArmor(3, "SSR Armor", DropType.SSREquipment),
    SSRBracers(4, "SSR Bracers", DropType.SSREquipment),
    SSRBelt(5, "SSR Belt", DropType.SSREquipment),
    SSRHandguards(6, "SSR Handguards", DropType.SSREquipment),
    SSRLegguards(7, "SSR Legguards", DropType.SSREquipment),
    SSRSabatons(8, "SSR Sabatons", DropType.SSREquipment),

    SRHelmet(9, "SR Helmet", DropType.SREquipment),
    SRShoulderguards(10, "SR Shoulderguards", DropType.SREquipment),
    SRSuit(11, "SR Suit", DropType.SREquipment),
    SRArmbands(12, "SR Armbands", DropType.SREquipment),
    SRBelt(13, "SR Belt", DropType.SREquipment),
    SRGloves(14, "SR Gloves", DropType.SREquipment),
    SRLeggings(15, "SR Leggings", DropType.SREquipment),
    SRBoots(16, "SR Boots", DropType.SREquipment),

    HumaMatrix(17, "Huma Matrix", DropType.SSRMatrix),
    SamirMatrix(18, "Samir Matrix", DropType.SSRMatrix),
    KingMatrix(19, "KING Matrix", DropType.SSRMatrix),
    CrowMatrix(20, "Crow Matrix", DropType.SSRMatrix),
    MerylMatrix(21, "Meryl Matrix", DropType.SSRMatrix),
    ZeroMatrix(22, "Zero Matrix", DropType.SSRMatrix),
    CocoritterMatrix(23, "Cocoritter Matrix", DropType.SSRMatrix),
    ShiroMatrix(24, "Shiro Matrix", DropType.SSRMatrix),
    TsubasaMatrix(25, "Tsubasa Matrix", DropType.SSRMatrix),

    RobargMatrix(26, "Robarg Matrix", DropType.SRMatrix),
    BaiLangMatrix(27, "Bai Lang Matrix", DropType.SRMatrix),
    FrostBotMatrix(28, "Frost Bot Matrix", DropType.SRMatrix),
    EchoMatrix(29, "Echo Matrix", DropType.SRMatrix),
    ApophisMatrix(30, "Apophis Matrix", DropType.SRMatrix),
    HildaMatrix(31, "Hilda Matrix", DropType.SRMatrix),
    SobekMatrix(32, "Sobek Matrix", DropType.SRMatrix),
    EneMatrix(33, "Ene Matrix", DropType.SRMatrix),
    BarbarossaMatrix(34, "Barbarossa Matrix", DropType.SRMatrix),
    PepperMatrix(35, "Pepper Matrix", DropType.SRMatrix);

    companion object {
        fun getById(id: Int) = values().first { it.id == id }
        fun getByName(name: String) = values().first { it.itemName == name }
    }
}

enum class DropType(val typeName: String, val color: Color) {
    SREquipment("SR Equipment", PurpleSR),
    SSREquipment("SSR Equipment", OrangeSSR),
    SRMatrix("SR Matrix", PurpleSR),
    SSRMatrix("SSR Matrix", OrangeSSR)
}