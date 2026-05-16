package com.dsm.firebaseauth.di


import com.dsm.firebaseauth.data.repository.MusicRepository
import com.dsm.firebaseauth.presentacion.player.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val musicModule = module{
    single { MusicRepository() }
    viewModel { MusicViewModel(get()) }
}