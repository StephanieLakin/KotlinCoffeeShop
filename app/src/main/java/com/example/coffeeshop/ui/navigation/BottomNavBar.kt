package com.example.coffeeshop.ui.navigation


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


sealed class BottomNavItem(val route: String, val title: String, val icon: String) {
    data object Home : BottomNavItem("home", "Home", "☕")
    data object Favorites : BottomNavItem("favorites", "Favorites", "❤️")
    data object Cart : BottomNavItem("cart", "Cart", "🛒")
    data object Profile : BottomNavItem("profile", "Profile", "👤")
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color(0xFF3E2723)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Text(text = item.icon, fontSize = 20.sp) },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFC107),
                    selectedTextColor = Color(0xFFFFC107)
                )
            )
        }
    }
}
