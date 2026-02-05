package com.nak.learningandroid.repositoryimpl

import com.nak.learningandroid.model.Conversation
import com.nak.learningandroid.model.MoodType
import com.nak.learningandroid.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * For now this acts as a fake Room-backed data source.
 * Later you can replace the implementation with a DAO.
 */
class ChatRepositoryImpl : ChatRepository {

    override fun getConversations(): Flow<List<Conversation>> {
        // Pretend this comes from Room
        return flow {
            emit(
                listOf(
                    Conversation(
                        id = 1,
                        title = "Alex Rivers",
                        lastMessage = "Let's catch up later! The...",
                        timeAgo = "2M AGO",
                        moodLabel = "Happy",
                        moodEmoji = "😊",
                        moodType = MoodType.HAPPY,
                        isOnline = true
                    ),
                    Conversation(
                        id = 2,
                        title = "Design Team",
                        lastMessage = "New mood board is ready...",
                        timeAgo = "JUST NOW",
                        moodLabel = "Focused",
                        moodEmoji = "🔥",
                        moodType = MoodType.FOCUSED,
                        isGroup = true,
                        isOnline = true
                    ),
                    Conversation(
                        id = 3,
                        title = "Sarah Chen",
                        lastMessage = "Sent a photo",
                        timeAgo = "1H AGO",
                        moodLabel = "Chill",
                        moodEmoji = "🪴",
                        moodType = MoodType.CHILL
                    ),
                    Conversation(
                        id = 4,
                        title = "Marcus",
                        lastMessage = "Let's crush this workout! 💪",
                        timeAgo = "4H AGO",
                        moodLabel = "Energetic",
                        moodEmoji = "⚡",
                        moodType = MoodType.ENERGETIC
                    )
                )
            )
        }
    }
}


