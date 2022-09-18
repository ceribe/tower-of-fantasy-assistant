package com.kagamiapps.tofassistant.di

import android.app.Application
import androidx.room.Room
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.JOLootRepositoryImpl
import com.kagamiapps.tofassistant.data.ToFDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesToFDatabase(app: Application): ToFDatabase {
        return Room.databaseBuilder(
            app,
            ToFDatabase::class.java,
            "tof_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesJOLootRepository(db: ToFDatabase): JOLootRepository {
        return JOLootRepositoryImpl(db.joDao)
    }
}