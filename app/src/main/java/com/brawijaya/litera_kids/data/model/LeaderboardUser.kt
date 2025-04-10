package com.brawijaya.litera_kids.data.model

data class LeaderboardUser(
    val id: Int,
    val name: String,
    val username: String,
    val level: Int,
    val avatarResId: Int,
    val isCurrentUser: Boolean = false
)