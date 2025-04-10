package com.brawijaya.litera_kids.ui.leaderboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brawijaya.litera_kids.R
import com.brawijaya.litera_kids.data.model.LeaderboardUser

@Composable
fun LeaderboardItem(
    user: LeaderboardUser,
    position: Int,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (position) {
        1 -> Color(0xFFFFC857) // Gold
        2 -> Color(0xFFD6D6D6) // Silver
        3 -> Color(0xFFCD7F32) // Bronze
        else -> if (user.isCurrentUser) Color(0xFF78CCFF) else Color.White
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Position circle
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (position <= 3) Color.White.copy(alpha = 0.3f)
                        else Color(0xFF78D9FF)
                    )
            ) {
                Text(
                    text = position.toString(),
                    fontWeight = FontWeight.Bold,
                    color = if (position <= 3) Color.White else Color.White
                )
            }

            // Avatar
            Image(
                painter = painterResource(id = user.avatarResId),
                contentDescription = "Avatar for ${user.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(48.dp)
                    .clip(CircleShape)
            )

            // User info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.name,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF006699),
                    fontSize = 16.sp
                )
                Text(
                    text = "@${user.username}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            // Crown and level
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dino),
                    contentDescription = "Crown",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Level ${user.level}",
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}