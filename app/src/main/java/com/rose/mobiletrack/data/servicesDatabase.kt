package com.rose.mobiletrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rose.mobiletrack.model.services
import com.rose.mobiletrack.model.User
import kotlin.jvm.java

@Database(entities = [services::class, User::class], version = 3, exportSchema = false)
abstract class servicesDatabase : RoomDatabase() {
    abstract fun productDao(): servicesDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:servicesDatabase? = null

        fun getDatabase(context: Context): servicesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    servicesDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}