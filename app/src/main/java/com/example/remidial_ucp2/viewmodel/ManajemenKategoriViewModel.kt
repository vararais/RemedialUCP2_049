package com.example.remidial_ucp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidial_ucp2.repositori.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManajemenKategoriViewModel(private val repository: Repository) : ViewModel() {
    val listKategori: StateFlow<List<Kategori>> = repository.getAllKategori()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _deleteError = MutableStateFlow<String?>(null)
    val deleteError: StateFlow<String?> = _deleteError

    fun deleteKategori(kategori: Kategori, deleteBooks: Boolean) {
        viewModelScope.launch {
            val isSafe = repository.isKategoriSafeToDelete(kategori.id)
            if (isSafe) {
                repository.deleteKategori(kategori, deleteBooks)
                _deleteError.value = null
            } else {
                _deleteError.value = "msg_hapus_aman"
            }
        }
    }

    fun dismissError() {
        _deleteError.value = null
    }
}