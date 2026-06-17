package com.example.coffeeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import com.example.coffeeshop.data.CartManager
import com.example.coffeeshop.ui.screens.CoffeeDetailScreen
import com.example.coffeeshop.data.sampleCoffees
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.coffeeshop.data.CartItem
import com.example.coffeeshop.data.CartManager.cartItems


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
//                                    onAddToCart = {
//                                        // TODO: Add to cart later
//                                        println("Added ${coffee.name} to cart")
//                                    }
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
    val items = CartManager.cartItems
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723)),
//        contentAlignment = Alignment.Center
    ) {
        if (items.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Your cart is empty 🛒", color = Color.White)
            }
        } else {
            Column {
                Text(
                    text = "Your Cart (${items.size} items)",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(CartManager.cartItems) { item ->
                        CartItemRow(
                            item = item,
                            onRemoveFromCart = {
                                CartManager.removeFromCart(item.coffee)
                            }
                        )
                    }
                }
                // Total & Checkout Btn
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF5D4037))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        val totalPrice = cartItems.sumOf { it.coffee.price * it.quantity }
                        Text(
                            text = "Total: $${totalPrice}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { CartManager.clearCart() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Proceed to Checkout", color = Color.White)
                        }
                    }
                }
            }
        }
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
@Composable
fun CartItemRow(
    item: CartItem,
    onRemoveFromCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = item.coffee.name)
            Text(text = "Qty: ${item.quantity}")
        }

        IconButton(onClick = onRemoveFromCart) {
            Icon(Icons.Default.Delete, contentDescription = "Remove")
        }
    }
}