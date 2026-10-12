package com.example.foodpantryscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.foodpantryscanner.View.AppNavigation
import com.example.foodpantryscanner.ui.theme.FoodPantryScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodPantryScannerTheme {
                AppNavigation()
            }
        }
    }
}
