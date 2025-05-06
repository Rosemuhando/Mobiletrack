package com.rose.mobiletrack.repository

import com.rose.mobiletrack.data.UserDao
import com.rose.mobiletrack.model.User

class UserRepository(private val userDao: UserDao) {

    // Register User - Store Password Directly (Without Hashing)
    suspend fun registerUser(user: User) {
        // Store the password as is (not recommended for production apps)
        userDao.registerUser(user)
    }

    // Login User - Verify Password Directly
    suspend fun loginUser(email: String, enteredPassword: String): User? {
        val user = userDao.loginUser(email)
        return if (user != null && user.password == enteredPassword) {
            // Password matches, return the user
            user
        } else {
            // Invalid password or user does not exist
            null
        }
    }
}
