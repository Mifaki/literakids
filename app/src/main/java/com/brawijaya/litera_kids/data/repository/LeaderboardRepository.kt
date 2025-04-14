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

//    override suspend fun getLeaderboardUsers(): List<LeaderboardUser> = suspendCancellableCoroutine { continuation ->
//        usersRef.orderByChild("level").limitToLast(10).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                try {
//                    val users = mutableListOf<LeaderboardUser>()
//                    val currentUserId = auth.currentUser?.uid
//
//                    for (childSnapshot in snapshot.children) {
//                        val userId = childSnapshot.key ?: continue
//                        val fullName = childSnapshot.child("fullName").getValue(String::class.java) ?: ""
//                        val username = childSnapshot.child("username").getValue(String::class.java) ?: ""
//                        val level = childSnapshot.child("level").getValue(Long::class.java)?.toInt() ?: 0
//                        val avatarUrl = childSnapshot.child("avatarUrl").getValue(String::class.java) ?: ""
//
//                        val isCurrentUser = userId == currentUserId
//
//                        users.add(
//                            LeaderboardUser(
//                                id = userId,
//                                fullName = fullName,
//                                username = username,
//                                level = level,
//                                avatarUrl = avatarUrl,
//                                isCurrentUser = isCurrentUser
//                            )
//                        )
//                    }
//
//                    val sortedUsers = users.sortedByDescending { it.level }
//
//                    continuation.resume(sortedUsers)
//                } catch (e: Exception) {
//                    continuation.resumeWithException(e)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                continuation.resumeWithException(error.toException())
//            }
//        })
//    }

    override suspend fun getLeaderboardUsers(): List<LeaderboardUser> {
        val mockUsers = listOf(
            LeaderboardUser("user1", "Ayu Dewi", "ayudewi", 99, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbhbtC8h1ReRZQOE7NVdPKn4BqffMPnSwuEXQo"), // parent
            LeaderboardUser("user2", "Fahri Hidayat", "fahridayat", 90, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbt3zHtiL4c2qEqSxboNLtMmqjM0aPBoV79MeT"), // parent
            LeaderboardUser("user3", "Lala Rumi", "lalamimi", 44, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbjOUpG61nfgDOIWYitA3LQ64u5mekXCNESbJr"), // child
            LeaderboardUser("user4", "Salsabilla Putri", "salsabil", 41, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbaEDlw8sQTSmezx0byoN5GEAsajvrhpInOQPR"),
            LeaderboardUser("user5", "Ahmad Farhan", "ahmadfar", 19, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbaXFa6uWhEXzjHqS7BgE3LRejPL7svOBvQhK0"), // parent
            LeaderboardUser("user6", "Rafa Elvano", "rafaelvano", 37, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbjOUpG61nfgDOIWYitA3LQ64u5mekXCNESbJr"),
            LeaderboardUser("user7", "Anisa Lestari", "anisalestari", 21, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbg6p5QQA59kTf4GkScKhUkBMX3xEoyc8YkWjZ"), // parent
            LeaderboardUser("user8", "Budi Santoso", "budisantoso", 10, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbaDHxsmToIlI1EBcwtIQkzDx7GeQOLRWEtW2K"), // parent
            LeaderboardUser("user9", "Kalea Syakira", "inisyakira", 29, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbuYg78YWBtZxIlP0KgHLnzWN1JvTRO4jyChYk"),
            LeaderboardUser("user10", "Fayra Alesha", "feyysha", 28, "https://xnv0w60lib.ufs.sh/f/Z1GE9qOlYjvbbKqasZvLPGXrHnBthd0UmVJKkvgx5pf3I4Qe"),
            LeaderboardUser("user11", "Levi Annora", "leviannora", 7, "https://firebasestorage.googleapis.com/v0/b/fitly-test-app.appspot.com/o/avatars%2F2.png?alt=media&token=47feab1f-e1cd-4b90-b1dd-7310d768da06", true),
            LeaderboardUser("user12", "Adinda Febyola", "febydinda", 38, "https://firebasestorage.googleapis.com/v0/b/fitly-test-app.appspot.com/o/avatars%2F7.png?alt=media&token=7f31d3b4-55ec-46d5-b463-17e4c2da6929")
        )

        return mockUsers.sortedByDescending { it.level }
    }

}