package com.rose.mobiletrack.data

import androidx.room.*
import com.rose.mobiletrack.model.Booking
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: Booking)

    @Update
    suspend fun updateBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)

    @Query("SELECT * FROM booking ORDER BY id DESC")
    fun getAllBooking(): Flow<List<Booking>>

    @Query("SELECT * FROM booking WHERE id = :id")
    suspend fun getBookingById(id: Int): Booking?
}

