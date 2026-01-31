package com.example.remidial_ucp2

import android.app.Application
import com.example.remidial_ucp2.repositori.AppContainer
import com.example.remidial_ucp2.repositori.AppDataContainer

class PerpustakaanApp : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}