package com.kagamiapps.tofassistant.data.consts

import androidx.compose.ui.graphics.Color
import com.kagamiapps.tofassistant.R
import com.kagamiapps.tofassistant.ui.theme.OrangeSSR
import com.kagamiapps.tofassistant.ui.theme.PurpleSR

enum class Drop(val id: Int, val itemName: String, val type: DropType, val imageId: Int) {
    SSRHelm(1, "SSR Helm", DropType.SSREquipment, R.drawable.ssr_helm),
    SSRSpaulders(2, "SSR Spaulders", DropType.SSREquipment, R.drawable.ssr_spaulders),
    SSRArmor(3, "SSR Armor", DropType.SSREquipment, R.drawable.ssr_armor),
    SSRBracers(4, "SSR Bracers", DropType.SSREquipment, R.drawable.ssr_bracers),
    SSRBelt(5, "SSR Belt", DropType.SSREquipment, R.drawable.ssr_belt),
    SSRHandguards(6, "SSR Handguards", DropType.SSREquipment, R.drawable.ssr_handguards),
    SSRLegguards(7, "SSR Legguards", DropType.SSREquipment, R.drawable.ssr_legguards),
    SSRSabatons(8, "SSR Sabatons", DropType.SSREquipment, R.drawable.ssr_sabatons),

    SRHelmet(9, "SR Helmet", DropType.SREquipment, R.drawable.sr_helmet),
    SRShoulderguards(10, "SR Shoulderguards", DropType.SREquipment, R.drawable.sr_shoulderguards),
    SRSuit(11, "SR Suit", DropType.SREquipment, R.drawable.sr_suit),
    SRArmbands(12, "SR Armbands", DropType.SREquipment, R.drawable.sr_armbands),
    SRBelt(13, "SR Belt", DropType.SREquipment, R.drawable.sr_belt),
    SRGloves(14, "SR Gloves", DropType.SREquipment, R.drawable.sr_gloves),
    SRLeggings(15, "SR Leggings", DropType.SREquipment, R.drawable.sr_leggings),
    SRBoots(16, "SR Boots", DropType.SREquipment, R.drawable.sr_boots),

    HumaMatrix(17, "Huma Matrix", DropType.SSRMatrix, R.drawable.matrix_huma),
    SamirMatrix(18, "Samir Matrix", DropType.SSRMatrix, R.drawable.matrix_samir),
    KingMatrix(19, "KING Matrix", DropType.SSRMatrix, R.drawable.matrix_king),
    CrowMatrix(20, "Crow Matrix", DropType.SSRMatrix, R.drawable.matrix_crow),
    MerylMatrix(21, "Meryl Matrix", DropType.SSRMatrix, R.drawable.matrix_meryl),
    ZeroMatrix(22, "Zero Matrix", DropType.SSRMatrix, R.drawable.matrix_zero),
    CocoritterMatrix(23, "Cocoritter Matrix", DropType.SSRMatrix, R.drawable.matrix_coco),
    ShiroMatrix(24, "Shiro Matrix", DropType.SSRMatrix, R.drawable.matrix_shiro),
    TsubasaMatrix(25, "Tsubasa Matrix", DropType.SSRMatrix, R.drawable.matrix_tsubasa),

    RobargMatrix(26, "Robarg Matrix", DropType.SRMatrix, R.drawable.matrix_robarg),
    BaiLangMatrix(27, "Bai Lang Matrix", DropType.SRMatrix, R.drawable.matrix_bailing),
    FrostBotMatrix(28, "Frost Bot Matrix", DropType.SRMatrix, R.drawable.matrix_frostbot),
    EchoMatrix(29, "Echo Matrix", DropType.SRMatrix, R.drawable.matrix_echo),
    ApophisMatrix(30, "Apophis Matrix", DropType.SRMatrix, R.drawable.matrix_apophis),
    HildaMatrix(31, "Hilda Matrix", DropType.SRMatrix, R.drawable.matrix_hilda),
    SobekMatrix(32, "Sobek Matrix", DropType.SRMatrix, R.drawable.matrix_sobek),
    EneMatrix(33, "Ene Matrix", DropType.SRMatrix, R.drawable.matrix_ene),
    BarbarossaMatrix(34, "Barbarossa Matrix", DropType.SRMatrix, R.drawable.matrix_barbarossa),
    PepperMatrix(35, "Pepper Matrix", DropType.SRMatrix, R.drawable.matrix_pepper);

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