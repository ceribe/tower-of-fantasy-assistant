package com.kagamiapps.tofassistant.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JOLootDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLoot(loot: JOLoot)

    @Delete
    suspend fun deleteLoot(loot: JOLoot)

    @Query("DELETE FROM joloot WHERE id = :id")
    suspend fun deleteLootById(id: Int)

    @Query("SELECT * FROM joloot WHERE id = :id")
    suspend fun getLootById(id: Int): JOLoot?

    @Query("SELECT * FROM joloot")
    fun getLoots(): Flow<List<JOLoot>>

    @Query("SELECT * FROM joloot WHERE id = (SELECT MAX(id) from joloot)")
    suspend fun getLastLoot(): JOLoot?
}