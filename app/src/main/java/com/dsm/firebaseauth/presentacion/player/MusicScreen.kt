package com.dsm.firebaseauth.presentacion.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicScreen(viewModel: MusicViewModel=koinViewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            "Mi Reproductor de Musica",
            color=Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        //Lista de Musica
        SongList(viewModel,Modifier.weight(1f))
        //Controles del Reproductor
        PlayerSection(viewModel)
    }
}

@Composable
fun SongList(viewModel: MusicViewModel,modifier: Modifier=Modifier){
    val songs by viewModel.songs.collectAsState()
    LazyColumn(
        modifier=modifier) {
        itemsIndexed(songs){ index,song ->
            Text(
                text=song.title,
                color=Color.White,
                modifier= Modifier
                    .fillMaxWidth()
                    .clickable{viewModel.playSongAt(index)}
                    .padding(12.dp)
            )

        }//end itemsIndexed
    }//end lazycolumn
}

@Composable
fun PlayerSection(viewModel: MusicViewModel){
    val songs by viewModel.songs.collectAsState()
    val player by viewModel.player.collectAsState()
    player?.let{
        val song = songs.getOrNull(it.currentSongIndex)
        PlayerControls(
            song=song,
            isPlaying=it.isPlaying,
            onPlayPauseClick = {
                if(it.isPlaying) viewModel.pauseSong()
                else viewModel.resumeSong()
            },
            onNextClick = {
                viewModel.playNext()
            },
            onPreviousClick = {
                viewModel.playPrevious()
            }
        )
    }//end let
}//end function