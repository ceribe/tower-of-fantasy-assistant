package com.kagamiapps.tofassistant.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [JOLoot::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ToFDatabase: RoomDatabase() {

    abstract val joDao: JOLootDao
}