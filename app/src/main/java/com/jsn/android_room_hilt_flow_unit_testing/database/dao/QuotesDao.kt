package com.jsn.android_room_hilt_flow_unit_testing.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jsn.android_room_hilt_flow_unit_testing.database.entity.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {
    @Insert
    suspend fun insertQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote")
    suspend fun delete()

    @Query("SELECT * FROM quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM quote")
    fun getQuotesWithFlow(): Flow<List<Quote>>

    @Query("SELECT * FROM quote where id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): Quote
}