package com.nak.learningandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nak.learningandroid.model.User
import com.nak.learningandroid.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<User>) : UiState()
    data class Error(val message: String) : UiState()
}

class MainViewModel(
    val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchData().collect { result ->
                if (result.isEmpty()) {
                    _uiState.value = UiState.Success(result)
                } else {
                    _uiState.value = UiState.Error("No Data")
                }
            }
        }
    }

    //awaitItem -> Loading and Success

}