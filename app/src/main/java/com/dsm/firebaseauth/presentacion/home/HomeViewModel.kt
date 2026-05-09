package com.dsm.firebaseauth.presentacion.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.firebaseauth.model.Artist
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {
    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artists: StateFlow<List<Artist>> = _artists

    private var db: FirebaseFirestore = Firebase.firestore

    init{
        getArtists()
    }

    private suspend fun getAllArtists(): List<Artist> {
        return try{
            db.collection("artists").get().await()
                .documents.mapNotNull { document ->document.toObject(Artist::class.java) }
        }catch (e: Exception){
            Log.e("getAllArtists","Error al obtener artistas: ${e.message}",e)
            emptyList()
        }
    }

    private fun getArtists(){
        viewModelScope.launch {
            val result:List<Artist> = withContext(Dispatchers.IO){
                getAllArtists()
            }
            Log.d("getAllArtists","Artistas Cargados: ${result.size}")
            _artists.value = result
        }//end launch
    }//end function


}