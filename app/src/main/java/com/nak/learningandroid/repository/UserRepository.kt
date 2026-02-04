package com.nak.learningandroid.repository

import com.nak.learningandroid.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun fetchData(): Flow<List<User>>

}