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
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffeeshop.ui.screens.CoffeeDetailScreen
import com.example.coffeeshop.data.sampleCoffees


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
                        composable("home") {
                            HomeScreen(
                                onCoffeeClick = { coffee ->
                                    navController.navigate("detail/${coffee.id}")
                                }
                            )
                        }

                        composable(
                            route = "detail/{coffeeId}",
                            arguments = listOf(navArgument("coffeeId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val coffeeId = backStackEntry.arguments?.getString("coffeeId")
                            val coffee = sampleCoffees.find { it.id == coffeeId }

                            if (coffee != null) {
                                CoffeeDetailScreen(
                                    coffee = coffee,
                                    onBackClick = { navController.popBackStack() },
                                    onAddToCart = {
                                        println("Added ${coffee.name} to cart")
                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Coffee not found", color = Color.White)
                                }
                            }
                        }

                        composable("favorites") { FavoritesScreen() }
                        composable("cart") { CartScreen() }
                        composable("profile") { ProfileScreen() }
                    }
                }
            }
        }
    }
}

// Placeholder Screens
@Composable
fun FavoritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723)),
        contentAlignment = Alignment.Center
    ) {
        Text("❤️ Favorites (Coming Soon)", color = Color.White)
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
        Text("🛒 Cart (Coming Soon)", color = Color.White)
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
        Text("👤 Profile (Coming Soon)", color = Color.White)
    }
}