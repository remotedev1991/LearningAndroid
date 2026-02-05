package com.nak.learningandroid.model

data class Conversation(
    val id: Long,
    val title: String,
    val lastMessage: String,
    val timeAgo: String,
    val moodLabel: String,
    val moodEmoji: String,
    val moodType: MoodType,
    val isGroup: Boolean = false,
    val isOnline: Boolean = false
)

enum class MoodType {
    HAPPY,
    FOCUSED,
    CHILL,
    ENERGETIC
}


