package com.nak.learningandroid.repositoryimpl

import com.nak.learningandroid.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl: LoginRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Flow<String> {
        return flow {
            emit("Login Success")
        }
    }

}