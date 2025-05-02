package com.rose.mobiletrack.ui.screens.fare



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FareScreen(navController: NavController ) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var pickup by remember { mutableStateOf("") }
    var drop by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
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
            value = distance,
            onValueChange = { distance = it },
            label = { Text("Distance (in km)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,

            imeAction = ImeAction.Done


            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (name.isBlank() || pickup.isBlank() || drop.isBlank() || distance.isBlank()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val km = distance.toDoubleOrNull()
            if (km == null || km < 0) {
                Toast.makeText(context, "Please enter a valid distance", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val fare = if (km >= 1.0) 500 else 300
            result = """
                Customer: $name
                Pickup: $pickup
                Drop-off: $drop
                Distance: ${"%.2f".format(km)} km
                Fare: Ksh $fare
            """.trimIndent()
        }) {
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
fun FareScreenPreview(){
    FareScreen(rememberNavController())

}


