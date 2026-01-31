package com.example.remidial_ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidial_ucp2.repositori.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BukuViewModel(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle? = null
) : ViewModel() {
    var uiState by mutableStateOf(BukuUIState())
        private set

    val listKategori: StateFlow<List<Kategori>> = repository.getAllKategori()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val bukuId: Int? = savedStateHandle?.get<Int>("idBuku")

    init {
        if (bukuId != null) {
            viewModelScope.launch {
                repository.getBukuById(bukuId).collect { buku ->
                    uiState = buku.toUIState()
                }
            }
        }
    }

    fun updateUiState(buku: BukuUIState) {
        uiState = buku
    }

    fun saveBuku() {
        viewModelScope.launch {
            if (bukuId == null) {
                repository.insertBuku(uiState.toEntity())
            } else {
                repository.updateBuku(uiState.toEntity().copy(id = bukuId))
            }
        }
    }
}

data class BukuUIState(
    val judul: String = "",
    val penulis: String = "",
    val penerbit: String = "",
    val tahunTerbit: String = "",
    val status: String = "Tersedia",
    val idKategori: Int? = null
)

fun BukuUIState.toEntity(): Buku = Buku(
    judul = judul,
    penulis = penulis,
    penerbit = penerbit,
    tahunTerbit = tahunTerbit,
    status = status,
    idKategori = idKategori
)

fun Buku.toUIState(): BukuUIState = BukuUIState(
    judul = judul,
    penulis = penulis,
    penerbit = penerbit,
    tahunTerbit = tahunTerbit,
    status = status,
    idKategori = idKategori
)