package com.rose.mobiletrack.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rose.mobiletrack.model.services


@Dao
interface servicesDao {
    @Query("SELECT * FROM services")
    fun getAllservices(): LiveData<List<services>>

    @Insert
    suspend fun insertservices(service: services)

    @Update
    suspend fun updateServices( services: services)

    @Delete
    suspend fun deleteservices(services: services)
}
