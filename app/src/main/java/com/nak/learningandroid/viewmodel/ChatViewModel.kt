package com.nak.learningandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nak.learningandroid.model.Conversation
import com.nak.learningandroid.usecase.GetConversationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ChatUiState {
    object Loading : ChatUiState()
    data class Success(val conversations: List<Conversation>) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}

class ChatViewModel(
    private val getConversationsUseCase: GetConversationsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ChatUiState> =
        MutableStateFlow(ChatUiState.Loading)
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        loadConversations()
    }

    fun loadConversations() {
        viewModelScope.launch {
            getConversationsUseCase()
                .collect { conversations ->
                    _uiState.value = ChatUiState.Success(conversations)
                }
        }
    }
}


