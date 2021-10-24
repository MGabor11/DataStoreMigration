package com.example.datastoremigration.store

import kotlinx.coroutines.flow.Flow

interface UserStore {
    suspend fun saveUserName(name: String)
    fun getUserName(): Flow<String>
}
