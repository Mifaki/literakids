package com.brawijaya.litera_kids.ui.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brawijaya.litera_kids.data.model.LeaderboardUser
import com.brawijaya.litera_kids.domain.usecase.GetLeaderboardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val getLeaderboardUseCase: GetLeaderboardUseCase
) : ViewModel() {

    private val _leaderboardState = MutableStateFlow<LeaderboardState>(LeaderboardState.Loading)
    val leaderboardState: StateFlow<LeaderboardState> = _leaderboardState.asStateFlow()

    init {
        loadLeaderboard()
    }

    fun loadLeaderboard() {
        viewModelScope.launch {
            try {
                _leaderboardState.value = LeaderboardState.Loading
                val users = getLeaderboardUseCase()
                _leaderboardState.value = LeaderboardState.Success(users)
            } catch (e: Exception) {
                _leaderboardState.value = LeaderboardState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class LeaderboardState {
    data object Loading : LeaderboardState()
    data class Success(val users: List<LeaderboardUser>) : LeaderboardState()
    data class Error(val message: String) : LeaderboardState()
}