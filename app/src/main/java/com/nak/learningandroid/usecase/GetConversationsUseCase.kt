package com.nak.learningandroid.usecase

import com.nak.learningandroid.model.Conversation
import com.nak.learningandroid.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class GetConversationsUseCase(
    private val repository: ChatRepository
) {

    operator fun invoke(): Flow<List<Conversation>> = repository.getConversations()
}


