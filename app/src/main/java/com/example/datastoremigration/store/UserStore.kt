package com.example.datastoremigration.store

interface UserStore {
    fun saveUserName(name: String)
    fun getUserName(): String?
}
