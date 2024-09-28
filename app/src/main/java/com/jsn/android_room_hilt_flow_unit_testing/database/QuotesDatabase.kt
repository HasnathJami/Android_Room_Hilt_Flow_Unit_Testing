package com.jsn.android_room_hilt_flow_unit_testing.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsn.android_room_hilt_flow_unit_testing.database.dao.QuotesDao
import com.jsn.android_room_hilt_flow_unit_testing.database.entity.Quote

@Database(entities = [Quote::class], version = 2)
abstract class QuotesDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuotesDao
}