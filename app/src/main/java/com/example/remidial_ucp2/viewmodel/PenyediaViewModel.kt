package com.example.remidial_ucp2.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remidial_ucp2.PerpustakaanApp
import com.example.remidial_ucp2.view.HomeView

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasi().container.repository)
        }

        initializer {
            KategoriViewModel(aplikasi().container.repository)
        }

        initializer {
            ManajemenKategoriViewModel(aplikasi().container.repository)
        }

        initializer {
            BukuViewModel(aplikasi().container.repository)
        }

        initializer {
            BukuViewModel(
                repository = aplikasi().container.repository,
                savedStateHandle = createSavedStateHandle()
            )
        }
    }
}

fun CreationExtras.aplikasi(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)