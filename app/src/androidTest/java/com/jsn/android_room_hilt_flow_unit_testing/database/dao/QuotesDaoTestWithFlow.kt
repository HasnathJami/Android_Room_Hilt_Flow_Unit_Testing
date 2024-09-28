package com.jsn.android_room_hilt_flow_unit_testing.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.jsn.android_room_hilt_flow_unit_testing.database.QuotesDatabase
import com.jsn.android_room_hilt_flow_unit_testing.database.entity.Quote
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuotesDaoTestWithFlow {

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
        val result = quotesDao.getQuotesWithFlow().first() //it's working fine for first item
        //       val result = quotesDao.getQuotesWithFlow().toList() // it run's infinitely, to solve this we have to use turbine library


        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Test Quote", result[0].text)
    }

    @Test
    fun insertQuote_expectedSingleQuote_withTurbineLibrary() = runBlocking {
        val quote = Quote(0, "Test Quote", "Tester")
        quotesDao.insertQuote(quote)

        launch {
            delay(1000)
            val quote2 = Quote(0, "Test Quote 2", "Tester 2")
            quotesDao.insertQuote(quote2)
        }
        quotesDao.getQuotesWithFlow().test {
            val quoteListFirstUpdate = awaitItem()
            Assert.assertEquals(1, quoteListFirstUpdate.size)
            val quoteListSecondEmission = awaitItem()
            Assert.assertEquals(2, quoteListSecondEmission.size)
            cancel()
        }

    }

//    @Test
//    fun deleteQuote_expectedNoResults() = runBlocking {
//        val quote = Quote(0, "Test Quote", "Tester")
//
//        quotesDao.insertQuote(quote)
//        quotesDao.delete()
//        val result = quotesDao.getQuotesWithFlow()
//
//        Assert.assertEquals(0, result.size)
//    }

    @Before
    fun tearDown() {
        quotesDatabase.close()
    }

}