package com.brawijaya.litera_kids.data.repository

import com.brawijaya.litera_kids.data.model.LeaderboardUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.resume

interface LeaderboardRepository {
    suspend fun getLeaderboardUsers(): List<LeaderboardUser>
}

class LeaderboardRepositoryImpl : LeaderboardRepository {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")
    private val auth = FirebaseAuth.getInstance()

    override suspend fun getLeaderboardUsers(): List<LeaderboardUser> = suspendCancellableCoroutine { continuation ->
        usersRef.orderByChild("level").limitToLast(10).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val users = mutableListOf<LeaderboardUser>()
                    val currentUserId = auth.currentUser?.uid

                    for (childSnapshot in snapshot.children) {
                        val userId = childSnapshot.key ?: continue
                        val name = childSnapshot.child("name").getValue(String::class.java) ?: ""
                        val username = childSnapshot.child("username").getValue(String::class.java) ?: ""
                        val level = childSnapshot.child("level").getValue(Long::class.java)?.toInt() ?: 0
                        val avatarUrl = childSnapshot.child("avatarUrl").getValue(String::class.java) ?: ""

                        val isCurrentUser = userId == currentUserId

                        users.add(
                            LeaderboardUser(
                                id = userId,
                                name = name,
                                username = username,
                                level = level,
                                avatarUrl = avatarUrl,
                                isCurrentUser = isCurrentUser
                            )
                        )
                    }

                    val sortedUsers = users.sortedByDescending { it.level }

                    continuation.resume(sortedUsers)
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        })
    }
}