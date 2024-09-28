package com.jsn.android_room_hilt_flow_unit_testing.di

import android.content.Context
import androidx.room.Room
import com.jsn.android_room_hilt_flow_unit_testing.database.QuotesDatabase
import com.jsn.android_room_hilt_flow_unit_testing.database.dao.QuotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): QuotesDatabase {
        return Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            "quotes_database"
        ).fallbackToDestructiveMigration() // Optional: Handle migrations if needed
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteDao(database: QuotesDatabase): QuotesDao {
        return database.quoteDao()
    }
}