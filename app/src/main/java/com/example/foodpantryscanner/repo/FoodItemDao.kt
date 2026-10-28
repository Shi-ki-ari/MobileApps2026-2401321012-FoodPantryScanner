package com.example.foodpantryscanner.repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.foodpantryscanner.Data.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {
    @Insert
    fun insert(foodItem: FoodItem): Long

    @Query("SELECT * FROM food_items")
    fun getAllFoodItems(): Flow<List<FoodItem>>

    @Update
    fun update(foodItem: FoodItem)

    @Delete
    fun delete(foodItem: FoodItem)
}