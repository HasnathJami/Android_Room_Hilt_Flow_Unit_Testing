package com.jsn.android_room_hilt_flow_unit_testing.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jsn.android_room_hilt_flow_unit_testing.database.QuotesDatabase
import com.jsn.android_room_hilt_flow_unit_testing.database.entity.Quote
import com.jsn.android_room_hilt_flow_unit_testing.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuotesDaoTestWithLivedata {

    lateinit var quotesDatabase: QuotesDatabase
    lateinit var quotesDao: QuotesDao

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        quotesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuotesDatabase::class.java
        ).allowMainThreadQueries().build()

        quotesDao = quotesDatabase.quoteDao()
    }

    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {
        val quote = Quote(0, "Test Quote", "Tester")

        quotesDao.insertQuote(quote)
        val result = quotesDao.getQuotes().getOrAwaitValue()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Test Quote", result[0].text)
    }

    @Test
    fun deleteQuote_expectedNoResults() = runBlocking {
        val quote = Quote(0, "Test Quote", "Tester")

        quotesDao.insertQuote(quote)
        quotesDao.delete()
        val result = quotesDao.getQuotes().getOrAwaitValue()

        Assert.assertEquals(0, result.size)
    }

    @Before
    fun tearDown() {
        quotesDatabase.close()
    }

}