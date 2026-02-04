package com.nak.learningandroid.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(username: String, password: String): Flow<String>
}



