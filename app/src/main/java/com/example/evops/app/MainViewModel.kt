package com.example.evops.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evops.core.data.datastore.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(authDataStore: AuthDataStore) : ViewModel() {
    val authState =
        authDataStore.authState.stateIn(viewModelScope, SharingStarted.Lazily, initialValue = null)
}
