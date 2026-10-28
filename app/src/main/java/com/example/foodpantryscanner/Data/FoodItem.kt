package com.example.foodpantryscanner.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val sodiumContent: Float = 0f
)
