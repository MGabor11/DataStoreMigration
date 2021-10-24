package com.example.datastoremigration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoremigration.store.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userStore: UserStore
) : ViewModel() {

    val savedUserName = userStore.getUserName().asStateFlow("")

    fun saveUserName(userName: String) = viewModelScope.launchOnDefault {
        userStore.saveUserName(userName)
    }

    private fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> =
        stateIn(
            viewModelScope + Dispatchers.Default,
            SharingStarted.WhileSubscribed(),
            initialValue
        )

    private fun CoroutineScope.launchOnDefault(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(Dispatchers.Default, start, block)
}
