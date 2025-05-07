package com.rose.mobiletrack.ui.screens.ridedetails

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.rose.mobiletrack.navigation.ROUT_ABOUT
import com.rose.mobiletrack.ui.theme.blue1
import com.rose.mobiletrack.ui.theme.pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact Us", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = blue1)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = blue1) {
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate(ROUT_ABOUT) }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Need help? We’re here for you!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                ContactInfoItem(icon = Icons.Default.LocationOn, label = "Location", value = "Nairobi, Kenya")
                ContactInfoItem(icon = Icons.Default.Info, label = "Hours", value = "Mon - Fri, 8AM - 6PM")
                ContactInfoItem(icon = Icons.Default.Info, label = "Support", value = "24/7 Response on Email & Phone")

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:support@mobiletrack.com")
                            putExtra(Intent.EXTRA_SUBJECT, "Customer Support Inquiry")
                        }
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "No email app available", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C6FF))
                ) {
                    Icon(Icons.Default.Email, contentDescription = "Email")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Email Support", color = Color.White)
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:+254700000000")
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(10.dp),

                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0072FF))
                ) {
                    Icon(Icons.Default.Phone, contentDescription = "Phone")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call Support", color = Color.White)
                }
            }
        }
    )
}

@Composable
fun ContactInfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = label, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(12.dp))

            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen(rememberNavController())
}
