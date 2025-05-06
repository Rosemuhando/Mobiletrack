package com.rose.mobiletrack.ui.screens.profile

import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.pink

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current

    // Example user data
    val userName = "Jane Doe"
    val userPhone = "0700000000"
    val userEmail = "jane@example.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
       
    ) {
        // Avatar
        Image(
            painter = painterResource(id = R.drawable.profile2), // Replace with real image or Coil
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = userName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(text = userEmail, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = userPhone, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        // Edit profile button
        OutlinedButton(
            onClick = {
                // navController.navigate("edit_profile")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(pink),
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logout button
        Button(
            onClick = {
                // Clear user session or navigate to login
                // navController.navigate("login") { popUpTo("profile") { inclusive = true } }
            },
            colors = ButtonDefaults.buttonColors(pink),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Logout")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Log Out")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController= rememberNavController())
}