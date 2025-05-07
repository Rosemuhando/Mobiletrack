package com.rose.mobiletrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val pickup: String,
    val drop: String,
    val date: String,
    val description: String
)
