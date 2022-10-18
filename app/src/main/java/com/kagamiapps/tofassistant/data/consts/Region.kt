package com.kagamiapps.tofassistant.data.consts

enum class Region(val joDifficulties: List<JODifficulty>) {
    Aesperia(JODifficulty.aesperiaDifficulties),
    Vera(JODifficulty.veraDifficulties);

    companion object {
        fun getByName(name: String) = values().first { it.name == name }
    }
}