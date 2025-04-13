package com.brawijaya.litera_kids.data.model

data class LeaderboardUser(
    val id: String,
    val fullName: String,
    val username: String,
    val level: Int,
    val avatarUrl: String,
    val isCurrentUser: Boolean = false
)