package com.dsm.firebaseauth.data.repository

import com.dsm.firebaseauth.model.Player
import com.dsm.firebaseauth.model.Song
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class MusicRepository {
    private val db=FirebaseDatabase.getInstance().reference

    suspend fun getSongs(): List<Song> {
        val songsSnapshot = db.child("songs").get().await()
        return songsSnapshot.children.mapNotNull { it.getValue(Song::class.java) }
    }

    suspend fun getPlayer(): Player {
        val playerSnapshot = db.child("player").get().await()
        return playerSnapshot.getValue(Player::class.java) ?: Player()
    }

    fun updatePlayer(player:Player){
        db.child("player").setValue(player)
    }

}