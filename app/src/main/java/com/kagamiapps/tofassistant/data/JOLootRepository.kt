package com.kagamiapps.tofassistant.data

import kotlinx.coroutines.flow.Flow

interface JOLootRepository {

    suspend fun upsertLoot(loot: JOLoot)

    suspend fun deleteLoot(loot: JOLoot)

    suspend fun getLootById(id: Int): JOLoot?

    fun getLoots(): Flow<List<JOLoot>>
}