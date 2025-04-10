package com.brawijaya.litera_kids.domain.usecase

import com.brawijaya.litera_kids.data.model.LeaderboardUser
import com.brawijaya.litera_kids.data.repository.LeaderboardRepository

class GetLeaderboardUseCase(private val repository: LeaderboardRepository) {
    suspend operator fun invoke(): List<LeaderboardUser> {
        return repository.getLeaderboardUsers()
    }
}