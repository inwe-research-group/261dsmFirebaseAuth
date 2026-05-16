package com.dsm.firebaseauth.model

data class Player(
    val currentSongIndex: Int=-1,
    val isPlaying: Boolean=false,
    val position: Long=0L,
)
