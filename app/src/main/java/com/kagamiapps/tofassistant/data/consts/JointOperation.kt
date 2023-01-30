package com.kagamiapps.tofassistant.data.consts

enum class JointOperation(
    val id: Int,
    val instanceName: String,
    val region: Region,
    private val equipmentSR: List<Drop>,
    private val equipmentSSR: List<Drop>,
    private val matrixSR: List<Drop>,
    private val matrixSSR: List<Drop>
) {
    QuarantineArea(
        1,
        "Quarantine Area",
        Region.Aesperia,
        listOf(Drop.SRArmbands, Drop.SRSuit),
        listOf(Drop.SSRBracers, Drop.SSRArmor),
        listOf(Drop.BarbarossaMatrix, Drop.PepperMatrix),
        listOf(Drop.TsubasaMatrix, Drop.ShiroMatrix)
    ),
    DeepseaStronghold(
        2,
        "Deepsea Stronghold",
        Region.Aesperia,
        listOf(Drop.SRLeggings, Drop.SRGloves),
        listOf(Drop.SSRLegguards, Drop.SSRHandguards),
        listOf(Drop.FrostBotMatrix, Drop.EchoMatrix),
        listOf(Drop.KingMatrix, Drop.CrowMatrix)
    ),
    HyenasArena(
        3,
        "Hyenas Arena",
        Region.Aesperia,
        listOf(Drop.SRShoulderguards, Drop.SRHelmet),
        listOf(Drop.SSRSpaulders, Drop.SSRHelm),
        listOf(Drop.SobekMatrix, Drop.EneMatrix),
        listOf(Drop.CocoritterMatrix, Drop.ShiroMatrix)
    ),
    DeepseaProvingGround(
        4,
        "Deepsea Proving Ground",
        Region.Aesperia,
        listOf(Drop.SRBoots, Drop.SRBelt),
        listOf(Drop.SSRSabatons, Drop.SSRBelt),
        listOf(Drop.ApophisMatrix, Drop.HildaMatrix),
        listOf(Drop.MerylMatrix, Drop.ZeroMatrix)
    ),
    SpacetimeTrainingGround(
        5,
        "Spacetime Training Ground",
        Region.Aesperia,
        listOf(Drop.SRArmbands, Drop.SRSuit),
        listOf(Drop.SSRBracers, Drop.SSRArmor),
        listOf(Drop.RobargMatrix, Drop.BaiLangMatrix),
        listOf(Drop.HumaMatrix, Drop.SamirMatrix)
    ),
    TheEndGame(
        6,
        "The End Game",
        Region.Vera,
        Drop.values().filter { it.type == DropType.SREquipment },
        Drop.values().filter { it.type == DropType.SSREquipment },
        Drop.values().filter { it.type == DropType.SRMatrix },
        Drop.values().filter { it.type == DropType.SSRMatrix }
    ),
    SadnessValley(
        7,
        "Sadness Valley",
        Region.Vera,
        Drop.values().filter { it.type == DropType.SREquipment },
        Drop.values().filter { it.type == DropType.SSREquipment },
        Drop.values().filter { it.type == DropType.SRMatrix },
        Drop.values().filter { it.type == DropType.SSRMatrix }
    ),
    PursuitOfFate(
        8,
        "Pursuit Of Fate",
        Region.Vera,
        Drop.values().filter { it.type == DropType.SREquipment },
        Drop.values().filter { it.type == DropType.SSREquipment },
        Drop.values().filter { it.type == DropType.SRMatrix },
        Drop.values().filter { it.type == DropType.SSRMatrix }
    ),
    CarnivalParty(
        9,
        "Carnival Party",
        Region.Vera,
        Drop.values().filter { it.type == DropType.SREquipment },
        Drop.values().filter { it.type == DropType.SSREquipment },
        Drop.values().filter { it.type == DropType.SRMatrix },
        Drop.values().filter { it.type == DropType.SSRMatrix }
    );

    companion object {
        fun getById(id: Int) = values().first { it.id == id }
        fun getByName(name: String) = values().first { it.instanceName == name }
    }

    fun getAllDrops() = equipmentSR + equipmentSSR + matrixSR + matrixSSR
}

enum class JODifficulty(val level: Int, private val text: String) {
    L20(20, "20"),
    L25(25, "25"),
    L31(31, "31"),
    L37(37, "37"),
    L43(43, "43"),
    L50(50, "50"),
    L60(60, "60"),
    L70(70, "70"),
    L75(75, "75"),
    L80(80, "80"),
    L85(85, "85"),
    L90(90, "90");

    companion object {
        fun getByLevel(level: Int) = values().first { it.level == level }

        val aesperiaDifficulties
            get() = listOf(L20, L25, L31, L37, L43, L50, L60, L70)

        val veraDifficulties
            get() = listOf(L75, L80, L85, L90)
    }

    override fun toString(): String {
        return text
    }
}