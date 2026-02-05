package com.nak.learningandroid.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nak.learningandroid.repositoryimpl.ChatRepositoryImpl
import com.nak.learningandroid.ui.theme.LearningAndroidTheme
import com.nak.learningandroid.usecase.GetConversationsUseCase
import com.nak.learningandroid.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val chatRepository = ChatRepositoryImpl()
            val getConversationsUseCase = GetConversationsUseCase(chatRepository)
            val chatViewModel = ChatViewModel(getConversationsUseCase)
            LearningAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VibeChatScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = chatViewModel
                    )
                }
            }
        }
    }
}