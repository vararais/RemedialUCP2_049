package com.example.remidial_ucp2.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "buku",
    foreignKeys = [ForeignKey(
        entity = KategoriEntity::class,
        parentColumns = ["id"],
        childColumns = ["idKategori"]
    )]
)
data class BukuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val penulis: String,
    val penerbit: String,
    val tahunTerbit: String,
    val status: String,
    val idKategori: Int? = null
)