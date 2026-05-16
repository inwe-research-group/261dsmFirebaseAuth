package com.dsm.firebaseauth.presentacion.player

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.firebaseauth.data.repository.MusicRepository
import com.dsm.firebaseauth.model.Player
import com.dsm.firebaseauth.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class MusicViewModel(
    private val musicRepository: MusicRepository
) : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs

    private val _player= MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player

    private var mediaPlayer: MediaPlayer? = null

    init {
        loadSongs()
    }

    private fun loadSongs() {
        viewModelScope.launch {
            val songs = musicRepository.getSongs()
            if(_songs.value!=songs)     //actualiza el estado solo si hay cambios
                _songs.value = songs
            if(_player.value==null)     //si no hay player, lo crea
                _player.value=musicRepository.getPlayer()?: Player()
        }
    }

    fun playSongAt(index: Int) {
        if(index !in _songs.value.indices) return
        if(_player.value?.currentSongIndex==index && _player.value?.isPlaying==true)
            return
        val song = _songs.value[index]
        stopSong()
        //crear y configurar un reproductor de audio usando MediaPlayer en Android
        mediaPlayer = MediaPlayer().apply {
            setDataSource(song.url)
            prepareAsync()
            setOnPreparedListener {
                start()
                val newPlayer=Player(
                    currentSongIndex=index,
                    isPlaying=true,
                    position=0L
                )
                musicRepository.updatePlayer(newPlayer)
                _player.value=newPlayer
            }
        }//end mediaplayer
    }//end playSongAt funtion

    fun stopSong(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer=null
    }

    fun pauseSong(){
        mediaPlayer?.pause()
        _player.value?.let {
            if(it.isPlaying){  //solo actualizar si cambia
                val newPlayer=it.copy(isPlaying=false)
                musicRepository.updatePlayer(newPlayer)
                _player.value=newPlayer
            }//end if
        }//end let
    }//end function

    fun resumeSong(){
        mediaPlayer?.start()
        _player.value?.let {
            if(!it.isPlaying) {
                val newPlayer=it.copy(isPlaying=true)
                musicRepository.updatePlayer(newPlayer)
                _player.value=newPlayer
            }//end if
        }//end let
    }//end function

    fun playNext(){
        val current=_player.value?: return  //si no hay reproductor de musica activo entonce return
        val nextIndex=(current.currentSongIndex+1) % _songs.value.size
        playSongAt(nextIndex)
    }//end function

    fun playPrevious(){
        val current=_player.value?: return
        val previousIndex= if(current.currentSongIndex-1 < 0 ){
            _songs.value.size - 1
        }else{
            current.currentSongIndex-1
        }
        playSongAt(previousIndex)
    }//end function

    override fun onCleared() {
        super.onCleared()
        stopSong()
    }
}