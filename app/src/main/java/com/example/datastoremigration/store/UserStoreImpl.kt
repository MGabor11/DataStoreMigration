package com.example.datastoremigration.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@Singleton
class UserStoreImpl @Inject constructor(
    private val userPreferencesDataStore: DataStore<Preferences>
) : UserStore {

    private val USER_NAME = stringPreferencesKey(PREF_USER_NAME)

    override suspend fun saveUserName(name: String) {
        userPreferencesDataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }

    override fun getUserName(): Flow<String> = userPreferencesDataStore.data
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }.flowOn(Dispatchers.IO)

    companion object {
        private const val PREF_USER_NAME = "PREF_USER_NAME"
    }
}
