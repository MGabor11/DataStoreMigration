package com.example.datastoremigration

import androidx.lifecycle.ViewModel
import com.example.datastoremigration.store.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userStore: UserStore
) : ViewModel() {

    val savedUserName = MutableStateFlow("")

    init {
        getUserName()
    }

    private fun getUserName() {
        savedUserName.value = userStore.getUserName() ?: ""
    }

    fun saveUserName(userName: String) {
        userStore.saveUserName(userName)
        getUserName()
    }
}
