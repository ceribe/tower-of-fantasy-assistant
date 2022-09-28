package com.kagamiapps.tofassistant.data.consts

enum class JointOperation(
    val id: Int,
    val instanceName: String,
    val equipmentSR: List<Drop>,
    val equipmentSSR: List<Drop>,
    val matrixSR: List<Drop>,
    val matrixSSR: List<Drop>
) {
    QuarantineArea(
        1,
        "Quarantine Area",
        listOf(Drop.SRArmbands, Drop.SRSuit),
        listOf(Drop.SSRBracers, Drop.SSRArmor),
        listOf(Drop.BarbarossaMatrix, Drop.PepperMatrix),
        listOf(Drop.TsubasaMatrix, Drop.ShiroMatrix)
    ),
    DeepseaStronghold(
        2,
        "Deepsea Stronghold",
        listOf(Drop.SRLeggings, Drop.SRGloves),
        listOf(Drop.SSRLegguards, Drop.SSRHandguards),
        listOf(Drop.FrostBotMatrix, Drop.EchoMatrix),
        listOf(Drop.KingMatrix, Drop.CrowMatrix)
    ),
    HyenasArena(
        3,
        "Hyenas Arena",
        listOf(Drop.SRShoulderguards, Drop.SRHelmet),
        listOf(Drop.SSRSpaulders, Drop.SSRHelm),
        listOf(Drop.SobekMatrix, Drop.EneMatrix),
        listOf(Drop.CocoritterMatrix, Drop.ShiroMatrix)
    ),
    DeepseaProvingGround(
        4,
        "Deepsea Proving Ground",
        listOf(Drop.SRBoots, Drop.SRBelt),
        listOf(Drop.SSRSabatons, Drop.SSRBelt),
        listOf(Drop.ApophisMatrix, Drop.HildaMatrix),
        listOf(Drop.MerylMatrix, Drop.ZeroMatrix)
    ),
    SpacetimeTrainingGround(
        5,
        "Spacetime Training Ground",
        listOf(Drop.SRArmbands, Drop.SRSuit),
        listOf(Drop.SSRBracers, Drop.SSRArmor),
        listOf(Drop.RobargMatrix, Drop.BaiLangMatrix),
        listOf(Drop.HumaMatrix, Drop.SamirMatrix)
    );

    companion object {
        fun getById(id: Int) = values().first { it.id == id }
        fun getByName(name: String) = values().first { it.instanceName == name }
    }

    fun getAllDrops() = equipmentSR + equipmentSSR + matrixSR + matrixSSR
}

enum class JODifficulty(val level: Int) {
    I(20),
    II(25),
    III(31),
    IV(37),
    V(43),
    VI(50),
    VII(60),
    VIII(70);

    companion object {
        fun getByLevel(level: Int) = values().first { it.level == level }
    }
}