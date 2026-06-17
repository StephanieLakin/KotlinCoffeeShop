package com.example.coffeeshop.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object CartManager {
    //val cartItems = mutableStateListOf<Coffee>()  // List of items in the cart

    val cartItems = mutableStateListOf<CartItem>()
    val totalItems = mutableStateOf(0) // Total number of items in the cart

//    fun addToCart(coffee: Coffee) {
//        cartItems.add(coffee)
//        totalItems.value = cartItems.size
//        println("Added ${coffee.name} to cart") // For debugging
//    }
//
//    fun removeFromCart(coffee: Coffee) {
//        cartItems.remove(coffee)
//        totalItems.value = cartItems.size
//        println("Removed ${coffee.name} from cart") // For debugging
//    }
//
//    fun clearCart() {
//        CartManager.cartItems.clear()
//        CartManager.totalItems.value = 0
//        println("Cart cleared") // For debugging
//    }
fun addToCart(coffee: Coffee) {
    val existingItem = cartItems.find { it.coffee.id == coffee.id }

    if (existingItem != null) {
        existingItem.quantity++
    } else {
        cartItems.add(CartItem(coffee))
    }

    updateTotal()
    println("Added ${coffee.name} to cart")
}

    fun removeFromCart(coffee: Coffee) {
        val existingItem = cartItems.find { it.coffee.id == coffee.id }

        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                cartItems.remove(existingItem)
            }
        }

        updateTotal()
        println("Removed ${coffee.name} from cart")
    }

    fun clearCart() {
        cartItems.clear()
        totalItems.value = 0
        println("Cart cleared")
    }

    private fun updateTotal() {
        totalItems.value = cartItems.sumOf { it.quantity }
    }
}