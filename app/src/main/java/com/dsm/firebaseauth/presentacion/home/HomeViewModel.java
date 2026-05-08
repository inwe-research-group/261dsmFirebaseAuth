package com.dsm.firebaseauth.presentacion.home;

import static java.util.Collections.emptyList;

import com.dsm.firebaseauth.model.Artist;
import com.google.firebase.ktx.Firebase;
import java.util.List;
import kotlinx.coroutines.flow.StateFlow;

public class HomeViewModel : ViewModel(){
    private var db: FirebaseFirestore= Firebase.firestore

    private val _artist= MutableStateFlow<List<Artist>>(emptyList())
    val artist :StateFlow<List<Artist>> = _artist

}
