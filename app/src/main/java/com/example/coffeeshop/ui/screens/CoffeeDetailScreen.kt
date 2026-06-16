package com.example.coffeeshop.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.coffeeshop.data.Coffee
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeDetailScreen(
    coffee: Coffee,
    onAddToCart: (Coffee) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2723))
    ) {
        // Top Bar
        TopAppBar(
            title = { Text(
                text = "Coffee Selections",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF3E2723)
            )
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )
        {
            Text(
                text = coffee.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = coffee.category,
                color = Color(0xFFFFC107)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = coffee.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$${coffee.price}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFFFC107),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { onAddToCart(coffee) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
                ) {
                    Text("Add to Cart", color = Color.Black)
                }
            }
        }
    }
}