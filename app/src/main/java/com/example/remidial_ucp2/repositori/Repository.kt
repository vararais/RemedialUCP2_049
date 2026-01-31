package com.example.remidial_ucp2.repositori

import com.example.remidial_ucp2.room.BukuWithKategori
import com.example.remidial_ucp2.room.PerpustakaanDao
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertKategori(kategori: Kategori)
    fun getAllKategori(): Flow<List<Kategori>>
    suspend fun deleteKategori(kategori: Kategori, deleteBooks: Boolean)
    suspend fun isKategoriSafeToDelete(kategoriId: Int): Boolean

    suspend fun insertBuku(buku: Buku)
    suspend fun updateBuku(buku: Buku)
    fun getAllBuku(): Flow<List<BukuWithKategori>>
    fun getBukuById(id: Int): Flow<Buku>
}

class LocalRepository(private val dao: PerpustakaanDao) : Repository {
    override suspend fun insertKategori(kategori: Kategori) = dao.insertKategori(kategori)
    override fun getAllKategori(): Flow<List<Kategori>> = dao.getAllKategori()

    override suspend fun isKategoriSafeToDelete(kategoriId: Int): Boolean {
        return dao.countBukuDipinjam(kategoriId) == 0
    }

    override suspend fun deleteKategori(kategori: Kategori, deleteBooks: Boolean) {
        if (deleteBooks) {
            dao.deleteBukuByKategori(kategori.id)
        } else {
            dao.removeKategoriFromBuku(kategori.id)
        }
        dao.deleteKategori(kategori)
    }

    override suspend fun insertBuku(buku: Buku) = dao.insertBuku(buku)
    override suspend fun updateBuku(buku: Buku) = dao.updateBuku(buku)
    override fun getAllBuku(): Flow<List<BukuWithKategori>> = dao.getAllBukuWithKategori()
    override fun getBukuById(id: Int): Flow<Buku> = dao.getBukuById(id)
}