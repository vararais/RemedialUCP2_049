package com.example.remidial_ucp2.navigasi

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remidial_ucp2.view.HomeView
import com.example.remidial_ucp2.view.InsertBukuView
import com.example.remidial_ucp2.view.InsertKategoriView
import com.example.remidial_ucp2.view.ManajemenKategoriView

@Composable
fun PengelolaHalaman() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(
                onNavigateToInsertBuku = { navController.navigate("insert_buku") },
                onNavigateToManajemenKategori = { navController.navigate("manajemen_kategori") },
                onNavigateToUpdateBuku = { id -> navController.navigate("update_buku/$id") }
            )
        }
        composable("insert_buku") {
            InsertBukuView(onBack = { navController.popBackStack() })
        }
        composable(
            "update_buku/{idBuku}",
            arguments = listOf(navArgument("idBuku") { type = NavType.IntType })
        ) {
            InsertBukuView(onBack = { navController.popBackStack() })
        }
        composable("manajemen_kategori") {
            ManajemenKategoriView(
                onBack = { navController.popBackStack() },
                onNavigateToInsertKategori = { navController.navigate("insert_kategori") }
            )
        }
        composable("insert_kategori") {
            InsertKategoriView(onBack = { navController.popBackStack() })
        }
    }
}

