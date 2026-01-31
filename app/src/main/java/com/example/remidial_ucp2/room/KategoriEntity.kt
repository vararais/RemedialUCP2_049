package com.example.remidial_ucp2.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")
data class KategoriEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val deskripsi: String
)