package com.example.coffeeshop.data

data class CartItem(
    val coffee: Coffee,
    var quantity: Int = 1
)