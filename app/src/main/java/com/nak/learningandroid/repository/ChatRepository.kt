package com.nak.learningandroid.repository

import com.nak.learningandroid.model.Conversation
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getConversations(): Flow<List<Conversation>>
}


