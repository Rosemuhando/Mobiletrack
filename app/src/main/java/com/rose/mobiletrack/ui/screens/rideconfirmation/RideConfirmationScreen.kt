package com.rose.mobiletrack.ui.screens.rideconfirmation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.blue1
import com.rose.mobiletrack.ui.theme.pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiderConfirmationScreen(
    navController: NavController,
    name: String,
    phone: String,
    pickup: String,
    drop: String,
    distance: Double,
    fare: Int
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirm Your Ride") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = blue1,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 6.dp,
                color = blue1,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Ride Cancelled", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancel", color = blue1)
                    }

                    Button(
                        onClick = {
                            Toast.makeText(context, "Ride Confirmed!", Toast.LENGTH_SHORT).show()
                            // navController.navigate("ride_success")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Confirm", color = blue1)
                    }
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Ride Summary",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = blue1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        DetailRow("Name", name)
                        DetailRow("Phone", phone)
                        DetailRow("Pickup", pickup)
                        DetailRow("Drop-off", drop)
                        DetailRow("Distance", "%.2f km".format(distance))
                        DetailRow("Fare", "Ksh $fare")
                    }
                }
            }
        }
    )
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RiderConfirmationScreenPreview() {
    RiderConfirmationScreen(
        navController = rememberNavController(),
        name = "Jane Doe",
        phone = "0700000000",
        pickup = "Westlands",
        drop = "Kasarani",
        distance = 8.5,
        fare = 500
    )
}
