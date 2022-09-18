package com.kagamiapps.tofassistant.data.consts

enum class Drop(val id: Int, val itemName: String) {
    SSRHelm(1, "SSR Helm"),
    SSRSpaulders(2, "SSR Spaulders"),
    SSRArmor(3, "SSR Armor"),
    SSRBracers(4, "SSR Bracers"),
    SSRBelt(5, "SSR Belt"),
    SSRHandguards(6, "SSR Handguards"),
    SSRLegguards(7, "SSR Legguards"),
    SSRSabatons(8, "SSR Sabatons"),

    SRHelmet(9, "SR Helmet"),
    SRShoulderguards(10, "SR Shoulderguards"),
    SRSuit(11, "SR Suit"),
    SRArmbands(12, "SR Armbands"),
    SRBelt(13, "SR Belt"),
    SRGloves(14, "SR Gloves"),
    SRLeggings(15, "SR Leggings"),
    SRBoots(16, "SR Boots"),

    HumaMatrix(17, "Huma Matrix"),
    SamirMatrix(18, "Samir Matrix"),
    KingMatrix(19, "KING Matrix"),
    CrowMatrix(20, "Crow Matrix"),
    MerylMatrix(21, "Meryl Matrix"),
    ZeroMatrix(22, "ZeroMatrix"),
    CocoritterMatrix(23, "Cocoritter Matrix"),
    ShiroMatrix(24, "Shiro Matrix"),
    TsubasaMatrix(25, "Tsubasa Matrix"),

    RobargMatrix(26, "Robarg Matrix"),
    BaiLangMatrix(27, "Bai Lang Matrix"),
    FrostBotMatrix(28, "Frost Bot Matrix"),
    EchoMatrix(29, "Echo Matrix"),
    ApophisMatrix(30, "Apophis Matrix"),
    HildaMatrix(31, "Hilda Matrix"),
    SobekMatrix(32, "Sobek Matrix"),
    EneMatrix(33, "Ene Matrix"),
    BarbarossaMatrix(34, "Barbarossa Matrix"),
    PepperMatrix(35, "Pepper Matrix");

    companion object {
        fun getById(id: Int) = values().first { it.id == id }
    }
}