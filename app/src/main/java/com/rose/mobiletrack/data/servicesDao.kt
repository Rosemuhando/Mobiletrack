package com.rose.mobiletrack.data


    import androidx.lifecycle.LiveData
    import androidx.room.*
    import com.rose.mobiletrack.model.services
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface servicesDao {
        @Query("SELECT * FROM services")
        fun getAllservices(): LiveData<List<services>>

        @Insert
        suspend fun insertservices(services: services)

        @Update
        suspend fun updateservices(services: services)

        @Delete
        suspend fun deleteservices(services: services)
    }
