package com.brawijaya.litera_kids.ui.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brawijaya.litera_kids.data.model.LeaderboardUser
import com.brawijaya.litera_kids.data.repository.LeaderboardRepositoryImpl
import com.brawijaya.litera_kids.domain.usecase.GetLeaderboardUseCase
import com.brawijaya.litera_kids.ui.leaderboard.components.LeaderboardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    viewModel: LeaderboardViewModel = viewModel {
        LeaderboardViewModel(GetLeaderboardUseCase(LeaderboardRepositoryImpl()))
    },
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val leaderboardState by viewModel.leaderboardState.collectAsState()

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF58C6FA),
            Color(0xFFA892F3)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Papan Peringkat",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(gradientBackground)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (leaderboardState) {
                is LeaderboardState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is LeaderboardState.Error -> {
                    Text(
                        text = (leaderboardState as LeaderboardState.Error).message,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                is LeaderboardState.Success -> {
                    val allUsers = (leaderboardState as LeaderboardState.Success).users

                    val currentUser = allUsers.find { it.isCurrentUser }
                    val currentUserPosition = allUsers.indexOfFirst { it.isCurrentUser } + 1

                    LeaderboardContentWithStickyUser(
                        allUsers = allUsers,
                        currentUser = currentUser,
                        currentUserPosition = currentUserPosition,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun LeaderboardContentWithStickyUser(
    allUsers: List<LeaderboardUser>,
    currentUser: LeaderboardUser?,
    currentUserPosition: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFF8F8F8),
                    Color.White
                )
            )
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFA892F3).copy(alpha = 0.3f),
                                Color(0xFFF8F8F8)
                            )
                        )
                    )
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Top 10",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF006699)
                )
                Text(
                    text = "Yuk, Asah Terus Minat Bacamu Dan Jadi Yang Terbaik!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        bottom = 80.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                ) {
                    itemsIndexed(allUsers) { index, user ->
                        if (!user.isCurrentUser) {
                            LeaderboardItem(
                                user = user,
                                position = index + 1,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        currentUser?.let { user ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                LeaderboardItem(
                    user = user,
                    position = currentUserPosition,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}