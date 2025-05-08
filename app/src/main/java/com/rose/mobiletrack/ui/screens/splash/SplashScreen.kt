package com.rose.mobiletrack.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_LOGIN
import com.rose.mobiletrack.ui.theme.blue1 // Import the blue color
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Navigate after 2.5 seconds
    LaunchedEffect(Unit) {
        delay(2500) // Delay for 2.5 seconds
        navController.navigate(ROUT_LOGIN) {
            // Pop up the splash screen from the back stack so it won't be visible again
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(blue1) // Set the background color to blue1
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Place text above the image
            Text(
                text = "MobileTrack",
                fontSize = 30.sp,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White // Make the text color white for contrast
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Make the image circular
            Image(
                painter = painterResource(id = R.drawable.car), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(600.dp) // Set the size of the image
                    .clip(CircleShape ) // Apply circular shape
            )

            Spacer(modifier = Modifier.height(20.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                color = Color.White // Make the progress indicator color white for visibility
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}