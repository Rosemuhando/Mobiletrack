package com.rose.mobiletrack.repository


import com.rose.mobiletrack.data.UserDao
import com.rose.mobiletrack.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}