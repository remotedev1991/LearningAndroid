package com.nak.learningandroid.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nak.learningandroid.R
import com.nak.learningandroid.model.Conversation
import com.nak.learningandroid.model.MoodType
import com.nak.learningandroid.repositoryimpl.ChatRepositoryImpl
import com.nak.learningandroid.ui.theme.LearningAndroidTheme
import com.nak.learningandroid.usecase.GetConversationsUseCase
import com.nak.learningandroid.viewmodel.ChatUiState
import com.nak.learningandroid.viewmodel.ChatViewModel

@Composable
fun VibeChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFF02131C)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                VibeChatTopBar()
                Spacer(modifier = Modifier.height(24.dp))
                VibeChatTabs()
                Spacer(modifier = Modifier.height(16.dp))
                VibeChatSearchBar()
                Spacer(modifier = Modifier.height(24.dp))

                when (uiState) {
                    is ChatUiState.Loading -> {
                        Text(
                            text = "Loading...",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    is ChatUiState.Error -> {
                        Text(
                            text = (uiState as ChatUiState.Error).message,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    is ChatUiState.Success -> {
                        val conversations = (uiState as ChatUiState.Success).conversations
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(conversations) { conversation ->
                                ConversationRow(conversation = conversation)
                            }
                        }
                    }
                }
            }

            VibeChatFloatingButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )

            VibeChatBottomNavigation(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

// Small reusable composables

@Composable
fun VibeChatTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarCircle(
                modifier = Modifier.size(56.dp),
                borderColor = Color(0xFF00E4FF)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Vibe Chat",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }

        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "New message",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF071F2F))
                .padding(8.dp)
        )
    }
}

@Composable
fun VibeChatTabs(modifier: Modifier = Modifier) {
    val (selected, onSelectedChange) = remember { mutableStateOf("Chats") }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF061926)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            VibeTabChip(
                label = "Chats",
                isSelected = selected == "Chats",
                onClick = { onSelectedChange("Chats") },
                modifier = Modifier
                    .weight(1f)
            )
            VibeTabChip(
                label = "Global Mood",
                isSelected = selected == "Global Mood",
                onClick = { onSelectedChange("Global Mood") },
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun VibeTabChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (isSelected) {
        Brush.horizontalGradient(
            listOf(
                Color(0xFF00E4FF),
                Color(0xFF00FFA3)
            )
        )
    } else {
        null
    }

    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(28.dp))
            .then(
                if (background != null) {
                    Modifier
                        .background(brush = background)
                } else {
                    Modifier
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (isSelected) Color(0xFF02131C) else Color(0xFF8BA2B4),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun VibeChatSearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF061926))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color(0xFF607A90)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = "Search conversations",
            color = Color(0xFF607A90),
            fontSize = 14.sp
        )
    }
}

@Composable
fun ConversationRow(
    conversation: Conversation,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF061926))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarCircle(
            modifier = Modifier.size(56.dp),
            borderColor = when (conversation.moodType) {
                MoodType.HAPPY -> Color(0xFF00FFB8)
                MoodType.FOCUSED -> Color(0xFFFF8A3D)
                MoodType.CHILL -> Color(0xFFB088FF)
                MoodType.ENERGETIC -> Color(0xFFFFD447)
            }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = conversation.title,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = conversation.timeAgo,
                    color = Color(0xFF6F8497),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = conversation.lastMessage,
                color = Color(0xFF8BA2B4),
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        MoodPill(
            emoji = conversation.moodEmoji,
            label = conversation.moodLabel,
            moodType = conversation.moodType
        )
    }
}

@Composable
fun AvatarCircle(
    modifier: Modifier = Modifier,
    borderColor: Color
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(borderColor.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size((modifier.size / 1.3f).coerceAtLeast(40.dp))
                .clip(CircleShape)
                .background(Color(0xFF02131C)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun MoodPill(
    emoji: String,
    label: String,
    moodType: MoodType,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (moodType) {
        MoodType.HAPPY -> Color(0xFF013422)
        MoodType.FOCUSED -> Color(0xFF3A200C)
        MoodType.CHILL -> Color(0xFF261B3F)
        MoodType.ENERGETIC -> Color(0xFF362100)
    }

    val contentColor = when (moodType) {
        MoodType.HAPPY -> Color(0xFF00FFB8)
        MoodType.FOCUSED -> Color(0xFFFF8A3D)
        MoodType.CHILL -> Color(0xFFC2A7FF)
        MoodType.ENERGETIC -> Color(0xFFFFD447)
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = emoji, fontSize = 14.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = label,
            color = contentColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun VibeChatBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFF02131C)
    ) {
        val items = listOf("Chats", "Groups", "Discover", "Settings")
        val selectedIndex = 0

        items.forEachIndexed { index, label ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { },
                icon = {
                    // Reusing simple shapes instead of actual icons for now
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == selectedIndex) Color(0xFF00E4FF)
                                else Color(0xFF2A3C4B)
                            )
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF02131C),
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF8BA2B4),
                    unselectedTextColor = Color(0xFF8BA2B4),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun VibeChatFloatingButton(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { },
        modifier = modifier,
        containerColor = Color(0xFF00E4FF)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "New chat",
            tint = Color(0xFF02131C)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VibeChatScreenPreview() {
    val repository = ChatRepositoryImpl()
    val useCase = GetConversationsUseCase(repository)
    val viewModel = ChatViewModel(useCase)

    LearningAndroidTheme {
        VibeChatScreen(viewModel = viewModel)
    }
}


