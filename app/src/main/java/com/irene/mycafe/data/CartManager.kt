package com.irene.mycafe.data

import androidx.compose.runtime.mutableStateListOf

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int // Changed to val for immutability
)

object CartManager {
    val items = mutableStateListOf<CartItem>()

    fun addToCart(menuItem: MenuItem) {
        val index = items.indexOfFirst { it.menuItem.id == menuItem.id }
        if (index != -1) {
            val currentItem = items[index]
            items[index] = currentItem.copy(quantity = currentItem.quantity + 1)
        } else {
            items.add(CartItem(menuItem, 1))
        }
    }

    fun removeFromCart(menuItem: MenuItem) {
        val index = items.indexOfFirst { it.menuItem.id == menuItem.id }
        if (index != -1) {
            val currentItem = items[index]
            if (currentItem.quantity > 1) {
                items[index] = currentItem.copy(quantity = currentItem.quantity - 1)
            } else {
                items.removeAt(index)
            }
        }
    }

    fun getTotalPrice(): Long {
        return items.sumOf { it.menuItem.price * it.quantity }
    }

    fun clearCart() {
        items.clear()
    }
}
