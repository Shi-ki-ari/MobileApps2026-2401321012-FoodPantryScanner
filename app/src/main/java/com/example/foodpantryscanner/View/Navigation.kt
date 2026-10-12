package com.example.foodpantryscanner.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(
                onNavigateToDetails = {
                    navController.navigate("details_screen")
                }
            )
        }
        composable("details_screen") {
            DetailsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
