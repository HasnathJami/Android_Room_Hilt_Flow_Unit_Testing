package com.jsn.android_room_hilt_flow_unit_testing.di

import android.content.Context
import androidx.room.Room
import com.jsn.android_room_hilt_flow_unit_testing.database.QuotesDatabase
import com.jsn.android_room_hilt_flow_unit_testing.database.dao.QuotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
class TestDatabaseModule {
    @Provides
    @Singleton
    fun providesTestDB(@ApplicationContext context: Context): QuotesDatabase {
        return Room.inMemoryDatabaseBuilder(context, QuotesDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideTestQuoteDao(database: QuotesDatabase): QuotesDao {
        return database.quoteDao()
    }
}