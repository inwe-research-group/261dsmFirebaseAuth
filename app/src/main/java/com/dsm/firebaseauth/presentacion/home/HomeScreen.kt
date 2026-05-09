package com.dsm.firebaseauth.presentacion.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dsm.firebaseauth.model.Artist

@Composable
fun HomeScreen(viewModel: HomeViewModel=HomeViewModel()){
    val artists: State<List<Artist>> = viewModel.artists.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.Black),
            verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Popular Artist",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow{
            items(artists.value){
                ArtistItem(
                    artist = it,
                    onItemSelected = {}
                )
            }

        }
    }
}

@Composable
fun ArtistItem(
    artist: Artist,
    onItemSelected: (Artist) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onItemSelected(artist) }
    ){
        //requiere la dependencia coil
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = artist.image,
            contentDescription = "Artist image",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = artist.name.orEmpty(),
            color = Color.White
        )
    }//end column

}//end fun

