package com.brawijaya.litera_kids.ui.leaderboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.brawijaya.litera_kids.R
import com.brawijaya.litera_kids.data.model.LeaderboardUser

@Composable
fun LeaderboardItem(
    user: LeaderboardUser,
    position: Int,
    modifier: Modifier = Modifier
) {
    val backgroundBrush = when (position) {
        1 -> Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFFCF25),
                Color(0x4DFFCF25)
            )
        )
        2 -> Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB0B0B0),
                Color(0x4DB0B0B0)
            )
        )
        3 -> Brush.verticalGradient(
            colors = listOf(
                Color(0xFFD57D3D),
                Color(0x4DD57D3D)
            )
        )
        else -> null
    }

    val backgroundColor = if (user.isCurrentUser) Color(0xFF78CCFF) else Color.White

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
                .then(
                    if (backgroundBrush != null) {
                        Modifier.background(backgroundBrush)
                    } else {
                        Modifier
                    }
                )
                .padding(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                if (position <= 3) {
                    val ringColor = when (position) {
                        1 -> Brush.radialGradient(
                            listOf(Color(0xFFFFCF25), Color(0x4DFFCF25)),
                            radius = 60f
                        )
                        2 -> Brush.radialGradient(
                            listOf(Color(0xFFB0B0B0), Color(0x4DB0B0B0)),
                            radius = 60f
                        )
                        else -> Brush.radialGradient(
                            listOf(Color(0xFFD57D3D), Color(0x4DD57D3D)),
                            radius = 60f
                        )
                    }
                    Canvas(
                        modifier = Modifier
                            .size(64.dp)
                            .zIndex(0f)
                    ) {
                        drawCircle(
                            brush = ringColor,
                            radius = size.minDimension / 2,
                            style = Stroke(width = 6f, cap = StrokeCap.Round)
                        )
                    }
                }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Avatar for ${user.fullName}",
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.user_placeholder),
                    placeholder = painterResource(id = R.drawable.user_placeholder),
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .zIndex(1f)
                )
                Surface(
                    shape = CircleShape,
                    color = when (position) {
                        1 -> Color(0xFFFFCF25)
                        2 -> Color(0xFFB0B0B0)
                        3 -> Color(0xFFD57D3D)
                        else -> Color(0xFF78D9FF)
                    },
                    modifier = Modifier
                        .size(22.dp).then(
                            if (position <= 3) {
                                Modifier.offset((-2).dp, (-2).dp)
                            } else {
                                Modifier.offset((-6).dp, (-6).dp)
                            }
                        )
                        .align(Alignment.TopStart)
                        .zIndex(4f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = position.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = user.fullName,
                    fontWeight = FontWeight.Bold,
                    color = if (position <= 3) Color(0xFF006699) else Color(0xFF006699),
                    fontSize = 16.sp
                )
                Text(
                    text = "@${user.username}",
                    color = if (position <= 3) Color.DarkGray else Color.Gray,
                    fontSize = 12.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (position <= 3) R.drawable.crown_white else R.drawable.crown_blue
                    ),
                    contentDescription = "Level icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Level ${user.level}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}