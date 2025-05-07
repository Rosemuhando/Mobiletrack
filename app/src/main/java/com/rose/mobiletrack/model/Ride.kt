package com.rose.mobiletrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rides")
data class Ride(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val pickup: String,
    val drop: String,
    val distance: Double,
    val fare: Int
)
