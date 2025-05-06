package com.rose.mobiletrack.ui.screens.tripinprogress


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextPainter.paint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.Pink80
import com.rose.mobiletrack.ui.theme.pink


@Composable
fun TripInProgressScreen(navController: NavController) {
    // Sample data for the trip
    val driverName = "John Doe"
    val driverCar = "Toyota Corolla"
    val licensePlate = "KBL 123D"
    val currentLocation = "Near Junction Mall"
    val estimatedArrival = "10 mins"
    var tripStatus by remember { mutableStateOf("In Progress") }

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,



    ) {
        // Trip Status
        Text(
            text = "Trip Status: $tripStatus",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = pink,
            fontSize = 35.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Driver Info
        Text(text = "Driver: $driverName", style = MaterialTheme.typography.titleMedium)
        Text(text = "Car: $driverCar", style = MaterialTheme.typography.bodyMedium)
        Text(text = "License Plate: $licensePlate", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Current Location
        Text(text = "Current Location: $currentLocation", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Estimated Arrival: $estimatedArrival", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Map Placeholder Image (This should be replaced with an actual map view)
        Image(
            painter = painterResource(id = R.drawable.ic_map_placeholder),
            contentDescription = "Map",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Cancel Trip Button
        Button(
            onClick = {
                // Handle cancel trip logic here
                // For example: navigate to cancellation screen or confirm cancellation
                navController.navigate("cancel_trip")
            },
            colors = ButtonDefaults.buttonColors(pink),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel Trip")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TripInProgressScreenPreview() {
    TripInProgressScreen(rememberNavController())

}