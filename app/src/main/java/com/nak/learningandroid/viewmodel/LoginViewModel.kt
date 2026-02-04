package com.nak.learningandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nak.learningandroid.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _userName = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val userName = _userName.asStateFlow()
    val password = _password.asStateFlow()

    fun updateUserName(name: String) {
        _userName.value = name
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun login() {
        viewModelScope.launch {

        }
    }
}