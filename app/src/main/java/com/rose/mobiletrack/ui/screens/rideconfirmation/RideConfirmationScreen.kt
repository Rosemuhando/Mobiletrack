package com.rose.mobiletrack.ui.screens.rideconfirmation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
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
import com.rose.mobiletrack.ui.theme.pink

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Confirm Your Ride",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = (40.sp),
            color = pink,
            fontWeight = FontWeight.ExtraBold
        )

        Column(modifier = Modifier.weight(1f)) {
            DetailRow("Name", name)
            DetailRow("Phone", phone)
            DetailRow("Pickup", pickup)
            DetailRow("Drop-off", drop)
            DetailRow("Distance", "%.2f km".format(distance))
            DetailRow("Fare", "Ksh $fare")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Ride Cancelled", Toast.LENGTH_SHORT).show()
                    navController.popBackStack() // Go back to previous screen
                },
                colors = ButtonDefaults.buttonColors(pink),
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Ride Confirmed!", Toast.LENGTH_SHORT).show()

                    // You could navigate to a "RideSuccessScreen" here
                    // navController.navigate("ride_success")
                },
                colors = ButtonDefaults.buttonColors(pink),
            ) {
                Text("Confirm")
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", fontWeight = FontWeight.SemiBold)
        Text(text = value)
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
