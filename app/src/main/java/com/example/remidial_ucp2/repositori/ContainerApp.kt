package com.example.remidial_ucp2.repositori

import android.content.Context
import com.example.remidial_ucp2.room.PerpustakaanDatabase

interface AppContainer {
    val repository: Repository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val repository: Repository by lazy {
        LocalRepository(PerpustakaanDatabase.getDatabase(context).perpustakaanDao())
    }
}