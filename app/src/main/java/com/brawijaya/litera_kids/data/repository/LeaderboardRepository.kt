package com.brawijaya.litera_kids.data.repository

import com.brawijaya.litera_kids.R
import com.brawijaya.litera_kids.data.model.LeaderboardUser

interface LeaderboardRepository {
    suspend fun getLeaderboardUsers(): List<LeaderboardUser>
}

class LeaderboardRepositoryImpl : LeaderboardRepository {
    override suspend fun getLeaderboardUsers(): List<LeaderboardUser> {
        // TODO: Implement firebase integration
        return listOf(
            LeaderboardUser(1, "Timo Kiko", "kikokikoo", 52, R.drawable.dino),
            LeaderboardUser(2, "Rani Zafa", "raniyfa", 49, R.drawable.dino),
            LeaderboardUser(3, "Lala Rumi", "lalamimi", 44, R.drawable.dino),
            LeaderboardUser(4, "Salsabilla Putri", "salsabil", 41, R.drawable.dino),
            LeaderboardUser(5, "Bimo Saputra", "bimbimoo", 38, R.drawable.dino),
            LeaderboardUser(6, "Rafa Elvano", "rafaelvano", 37, R.drawable.dino),
            LeaderboardUser(7, "Zea Kirana", "zeakirana", 35, R.drawable.dino),
            LeaderboardUser(8, "User", "eliohan04", 32, R.drawable.dino, true),
            LeaderboardUser(9, "Kalea Syakira", "inisyakira", 29, R.drawable.dino),
            LeaderboardUser(10, "Fayra Alesha", "feyysha", 28, R.drawable.dino)
        )
    }
}