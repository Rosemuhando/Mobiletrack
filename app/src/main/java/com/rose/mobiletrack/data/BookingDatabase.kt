package com.rose.mobiletrack.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rose.mobiletrack.model.Booking

@Database(entities = [Booking::class], version = 1, exportSchema = false)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao

    companion object {
        @Volatile private var INSTANCE: BookingDatabase? = null

        fun getDatabase(context: Context): BookingDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BookingDatabase::class.java,
                    "booking_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}