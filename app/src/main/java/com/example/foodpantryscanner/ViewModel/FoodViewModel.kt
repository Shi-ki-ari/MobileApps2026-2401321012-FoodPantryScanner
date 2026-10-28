package com.example.foodpantryscanner.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpantryscanner.Data.AppDatabase
import com.example.foodpantryscanner.Data.FoodItem
import com.example.foodpantryscanner.repo.FoodItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: FoodItemDao = AppDatabase.getDatabase(application).foodItemDao()

    val allFoodItems: StateFlow<List<FoodItem>> = dao.getAllFoodItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addFoodItem(name: String, protein: Int, fat: Int, carbs: Int, sodium: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(
                FoodItem(
                    name = name,
                    protein = protein,
                    fat = fat,
                    carbs = carbs,
                    sodiumContent = sodium
                )
            )
        }
    }

    fun updateFoodItem(id: Long, name: String, protein: Int, fat: Int, carbs: Int, sodium: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(
                FoodItem(
                    id = id, // keep the same ID so room knows which one to update
                    name = name,
                    protein = protein,
                    fat = fat,
                    carbs = carbs,
                    sodiumContent = sodium
                )
            )
        }
    }

    fun deleteFoodItem(food: FoodItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(food)
        }
    }
}