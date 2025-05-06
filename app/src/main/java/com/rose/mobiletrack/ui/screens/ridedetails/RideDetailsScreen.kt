package com.rose.mobiletrack.ui.screens.ridedetails

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.pink
import kotlin.random.Random

@Composable
fun RideDetailsScreen(navController: NavController) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var pickup by remember { mutableStateOf("") }
    var drop by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Distance will be calculated internally
    var calculatedDistance by remember { mutableStateOf(0.0) }

    fun calculateDistance(pickup: String, drop: String): Double {
        // Placeholder function – in a real app, use coordinates or an API
        return if (pickup.lowercase() != drop.lowercase()) {
            Random.nextDouble(0.5, 5.0)
        } else {
            0.0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Ride Details",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 40.sp,
            color = pink,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pickup,
            onValueChange = { pickup = it },
            label = { Text("Pick-up location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = drop,
            onValueChange = { drop = it },
            label = { Text("Drop-off location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Enter your phone number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

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

                val fare = if (calculatedDistance >= 1.0) 500 else 300
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
            colors = ButtonDefaults.buttonColors(pink),
        ) {
            Text("Calculate Fare")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (result.isNotEmpty()) {
            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RideDetailsScreenPreview() {
    RideDetailsScreen(navController = rememberNavController())
}
