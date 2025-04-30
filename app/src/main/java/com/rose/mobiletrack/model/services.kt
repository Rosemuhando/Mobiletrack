package com.rose.mobiletrack.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Servicer")
data class services(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val phone: String,
    val imagePath: String
)
