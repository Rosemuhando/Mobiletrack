package com.rose.mobiletrack.ui.screens.history


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.newwhite
import com.rose.mobiletrack.ui.theme.pink

// Define the RideHistory data class
data class RideHistory(
    val id: Int,
    val date: String,
    val pickupLocation: String,
    val dropLocation: String,
    val distance: Double,
    val fare: Double
)

// Sample data for history screen
val sampleHistory = listOf(
    RideHistory(1, "2025-05-01", "Downtown", "Airport", 15.5, 1200.0),
    RideHistory(2, "2025-05-02", "Mall", "Park", 5.0, 800.0),
    RideHistory(3, "2025-05-03", "Office", "Home", 7.2, 1000.0),
    RideHistory(4, "2025-05-04", "Station", "Museum", 12.3, 1500.0)
)

// Composable for displaying a single ride history item
@Composable
fun RideHistoryItem(ride: RideHistory,) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),



        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ride #${ride.id}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Date: ${ride.date}")
            Text("Pickup: ${ride.pickupLocation}")
            Text("Drop: ${ride.dropLocation}")
            Text("Distance: ${ride.distance} km")
            Text("Fare: Ksh ${ride.fare}")
        }
    }
}

// Composable for displaying the entire history list
@Composable
fun HistoryScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        Text("Ride History", style = MaterialTheme.typography.titleLarge,color = pink,fontWeight = FontWeight.ExtraBold,fontSize = (40.sp) )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn to display the list of rides
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleHistory) { ride ->
                RideHistoryItem(ride)
            }
        }
    }
}

// Preview the HistoryScreen
@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(rememberNavController())
}
