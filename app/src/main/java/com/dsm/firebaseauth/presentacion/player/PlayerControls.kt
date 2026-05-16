package com.dsm.firebaseauth.presentacion.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsm.firebaseauth.model.Song

@Composable
fun PlayerControls(
    song: Song?,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
    ){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = song?.title?:"Ninguna musica seleccionada",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(bottom=12.dp)
        )
        //Controles del Reproductor
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly //espacios iguales entre los botones
        ) {
            IconButton(
                onClick = { onPreviousClick() }
            ) {
                Icon(
                    imageVector=Icons.Default.SkipPrevious,
                    contentDescription = "Previous",
                    tint=Color.White,
                    modifier=Modifier.size(48.dp)
                )
            }

            IconButton(
                onClick = { onPlayPauseClick() }
            ) {
                Icon(
                    imageVector=if(isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause",
                    tint=Color.White,
                    modifier=Modifier.size(64.dp)
                )
            }

            IconButton(
                onClick = { onNextClick() }
            ) {
                Icon(
                    imageVector=Icons.Default.SkipNext,
                    contentDescription = "Next",
                    tint=Color.White,
                    modifier=Modifier.size(48.dp)
                )
            }






        }

    }

}