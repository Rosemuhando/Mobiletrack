package com.rose.mobiletrack.data



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rose.mobiletrack.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun loginUser(email: String): User?
}
