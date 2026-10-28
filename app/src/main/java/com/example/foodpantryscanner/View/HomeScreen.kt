package com.example.foodpantryscanner.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodpantryscanner.ViewModel.FoodViewModel

@Composable
fun HomeScreen(
    onNavigateToDetails: () -> Unit,
    viewModel: FoodViewModel = viewModel()
) {
    val foodList by viewModel.allFoodItems.collectAsState()

    // student variables lol
    var theName by remember { mutableStateOf("") }
    var theProtein by remember { mutableStateOf("") }
    var theFat by remember { mutableStateOf("") }
    var theCarbs by remember { mutableStateOf("") }
    var theSodium by remember { mutableStateOf("") }
    
    var isEditing by remember { mutableStateOf(false) }
    var editId by remember { mutableStateOf(0L) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp), // tight padding
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Food Pantry App!!!", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            Button(onClick = onNavigateToDetails) {
                Text(text = "Go to Details screen")
            }
            
            Spacer(modifier = Modifier.height(10.dp))
            
            // form 
            TextField(value = theName, onValueChange = { theName = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
            TextField(value = theProtein, onValueChange = { theProtein = it }, label = { Text("Protein (g)") }, modifier = Modifier.fillMaxWidth())
            TextField(value = theFat, onValueChange = { theFat = it }, label = { Text("Fat (g)") }, modifier = Modifier.fillMaxWidth())
            TextField(value = theCarbs, onValueChange = { theCarbs = it }, label = { Text("Carbs (g)") }, modifier = Modifier.fillMaxWidth())
            TextField(value = theSodium, onValueChange = { theSodium = it }, label = { Text("Sodium (mg)") }, modifier = Modifier.fillMaxWidth())

            Button(
                onClick = {
                    val p = theProtein.toIntOrNull() ?: 0
                    val f = theFat.toIntOrNull() ?: 0
                    val c = theCarbs.toIntOrNull() ?: 0
                    val s = theSodium.toFloatOrNull() ?: 0f

                    if (isEditing) {
                        viewModel.updateFoodItem(editId, theName, p, f, c, s)
                        isEditing = false
                        editId = 0L
                    } else {
                        viewModel.addFoodItem(theName, p, f, c, s)
                    }

                    // reset
                    theName = ""
                    theProtein = ""
                    theFat = ""
                    theCarbs = ""
                    theSodium = ""
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                if (isEditing) {
                    Text("UPDATE ITEM")
                } else {
                    Text("ADD ITEM")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("My Foods:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            
            // list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(foodList) { food ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color.LightGray)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = food.name, fontWeight = FontWeight.Bold)
                            Text(text = "P: ${food.protein} F: ${food.fat} C: ${food.carbs} Na: ${food.sodiumContent}")
                        }
                        
                        Row {
                            Button(
                                onClick = {
                                    // populate form
                                    theName = food.name
                                    theProtein = food.protein.toString()
                                    theFat = food.fat.toString()
                                    theCarbs = food.carbs.toString()
                                    theSodium = food.sodiumContent.toString()
                                    isEditing = true
                                    editId = food.id
                                },
                                modifier = Modifier.padding(end = 4.dp)
                            ) {
                                Text("Edit")
                            }
                            
                            Button(
                                onClick = { viewModel.deleteFoodItem(food) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {
                                Text("X")
                            }
                        }
                    }
                }
            }
        }
    }
}
