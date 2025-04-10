package com.brawijaya.litera_kids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.brawijaya.litera_kids.data.repository.LeaderboardRepositoryImpl
import com.brawijaya.litera_kids.domain.usecase.GetLeaderboardUseCase
import com.brawijaya.litera_kids.ui.leaderboard.LeaderboardScreen
import com.brawijaya.litera_kids.ui.leaderboard.LeaderboardViewModel
import com.brawijaya.litera_kids.ui.theme.LiterakidsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val leaderboardRepository = LeaderboardRepositoryImpl()
        val getLeaderboardUseCase = GetLeaderboardUseCase(leaderboardRepository)

        setContent {
            LiterakidsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LeaderboardScreen(
                        viewModel = LeaderboardViewModel(getLeaderboardUseCase),
                        onBackClick = { finish() }
                    )
                }
            }
        }
    }
}