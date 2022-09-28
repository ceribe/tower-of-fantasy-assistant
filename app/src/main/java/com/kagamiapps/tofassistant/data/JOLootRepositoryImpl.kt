package com.kagamiapps.tofassistant.data

import kotlinx.coroutines.flow.Flow

class JOLootRepositoryImpl(
    private val dao: JOLootDao
): JOLootRepository {
    override suspend fun upsertLoot(loot: JOLoot) = dao.upsertLoot(loot)

    override suspend fun deleteLoot(loot: JOLoot) = dao.deleteLoot(loot)

    override suspend fun deleteLootById(id: Int) = dao.deleteLootById(id)

    override suspend fun getLootById(id: Int) = dao.getLootById(id)

    override fun getLoots(): Flow<List<JOLoot>> = dao.getLoots()

    override suspend fun getLastLoot(): JOLoot? = dao.getLastLoot()
}