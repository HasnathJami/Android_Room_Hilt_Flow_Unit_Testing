package com.jsn.android_room_hilt_flow_unit_testing.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Quote (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String
)