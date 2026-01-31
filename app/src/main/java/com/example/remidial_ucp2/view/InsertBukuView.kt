package com.example.remidial_ucp2.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remidial_ucp2.viewmodel.BukuViewModel
import com.example.remidial_ucp2.viewmodel.PenyediaViewModel
import kotlin.collections.find
import com.example.remidial_ucp2.R
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertBukuView(
    onBack: () -> Unit,
    viewModel: BukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val listKategori by viewModel.listKategori.collectAsState()

    var expandedKategori by remember { mutableStateOf(false) }
    var selectedKategoriName by remember { mutableStateOf("") }

    LaunchedEffect(uiState.idKategori, listKategori) {
        selectedKategoriName = listKategori.find { it.id == uiState.idKategori }?.nama ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_tambah_buku)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.judul,
                onValueChange = { viewModel.updateUiState(uiState.copy(judul = it)) },
                label = { Text(stringResource(R.string.label_judul)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.penulis,
                onValueChange = { viewModel.updateUiState(uiState.copy(penulis = it)) },
                label = { Text(stringResource(R.string.label_penulis)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.penerbit,
                onValueChange = { viewModel.updateUiState(uiState.copy(penerbit = it)) },
                label = { Text(stringResource(R.string.label_penerbit)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.tahunTerbit,
                onValueChange = { viewModel.updateUiState(uiState.copy(tahunTerbit = it)) },
                label = { Text(stringResource(R.string.label_tahun)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.status,
                onValueChange = { viewModel.updateUiState(uiState.copy(status = it)) },
                label = { Text(stringResource(R.string.label_status)) },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expandedKategori,
                onExpandedChange = { expandedKategori = !expandedKategori }
            ) {
                OutlinedTextField(
                    value = selectedKategoriName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.label_pilih_kategori)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedKategori) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedKategori,
                    onDismissRequest = { expandedKategori = false }
                ) {
                    listKategori.forEach { kategori ->
                        DropdownMenuItem(
                            text = { Text(kategori.nama) },
                            onClick = {
                                viewModel.updateUiState(uiState.copy(idKategori = kategori.id))
                                selectedKategoriName = kategori.nama
                                expandedKategori = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.saveBuku()
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}