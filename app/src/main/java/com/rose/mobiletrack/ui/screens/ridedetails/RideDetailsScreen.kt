package com.rose.mobiletrack.ui.screens.ridedetails

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_PAYMENT
import com.rose.mobiletrack.navigation.ROUT_SETTING
import com.rose.mobiletrack.ui.theme.blue1
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideDetailsScreen(navController: NavController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var pickup by remember { mutableStateOf("") }
    var drop by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var calculatedDistance by remember { mutableStateOf(0.0) }

    // Function to simulate the distance between pickup and drop-off points
    fun calculateDistance(pickup: String, drop: String): Double {
        return if (pickup.lowercase() != drop.lowercase()) {
            Random.nextDouble(0.5, 5.0) // Generate random distance between 0.5km and 5km
        } else 0.0 // If pickup and drop-off are the same, distance is 0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ride Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = blue1,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = blue1) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_HOME)}, // Navigate to Home screen
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.LightGray,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.LightGray
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ROUT_PAYMENT)
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = "Settings") },
                    label = { Text("Payment") }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Enter Ride Info",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = blue1
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Your Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pickup,
                    onValueChange = { pickup = it },
                    label = { Text("Pick-up Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = drop,
                    onValueChange = { drop = it },
                    label = { Text("Drop-off Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        if (name.isBlank() || pickup.isBlank() || drop.isBlank() || phone.isBlank()) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if (phone.length != 10 || !phone.all { it.isDigit() }) {
                            Toast.makeText(context, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        calculatedDistance = calculateDistance(pickup, drop)
                        val fare = if (calculatedDistance < 1.0) {
                            300 // If the distance is less than 1 km, use the 300 Ksh fare
                        } else {
                            (calculatedDistance * 500).toInt() // If the distance is 1 km or more, calculate fare by multiplying distance by 500 Ksh
                        }

                        result = """
            Name: $name
            Pickup: $pickup
            Drop-off: $drop
            Distance: ${"%.2f".format(calculatedDistance)} km
            Fare: Ksh $fare
            Phone: $phone
        """.trimIndent()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = blue1)
                ) {
                    Text("Calculate Fare")
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (result.isNotEmpty()) {
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RideDetailsScreenPreview() {
    RideDetailsScreen(rememberNavController())
}
