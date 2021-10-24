package com.example.datastoremigration.store

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStoreImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserStore {

    override fun saveUserName(name: String) {
        sharedPreferences.edit {
            putString(PREF_USER_NAME, name)
        }
    }

    override fun getUserName(): String? = sharedPreferences.getString(PREF_USER_NAME, "")

    companion object {
        private const val PREF_USER_NAME = "PREF_USER_NAME"
    }
}
