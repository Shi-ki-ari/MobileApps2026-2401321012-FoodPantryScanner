package com.example.foodpantryscanner.Data
import androidx.room3.Entity


@Entity(tableName = "food_items")
data class FoodItem(
    val id: Long = 0L,
    val name: String,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val sodiumContent: Float = 0f
)
