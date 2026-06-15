package com.example.coffeeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshop.ui.screens.HomeScreen
import com.example.coffeeshop.ui.theme.CoffeeShopTheme
import com.example.coffeeshop.ui.navigation.BottomNavBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("home") { HomeScreen() }
                        composable("favorites") { FavoritesScreen() }
                        composable("cart") { CartScreen() }
                        composable("profile") { ProfileScreen() }
                    }
                }
            }
        }
    }
}

// Improved Placeholder Screens
@Composable
fun FavoritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723)),
        contentAlignment = Alignment.Center
    ) {
        Text("❤️ Favorites Screen (Coming Soon)", color = Color.White)
    }
}

@Composable
fun CartScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723)),
        contentAlignment = Alignment.Center
    ) {
        Text("🛒 Cart Screen (Coming Soon)", color = Color.White)
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723)),
        contentAlignment = Alignment.Center
    ) {
        Text("👤 Profile Screen (Coming Soon)", color = Color.White)
    }
}