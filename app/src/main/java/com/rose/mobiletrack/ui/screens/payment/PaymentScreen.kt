package com.rose.mobiletrack.ui.screens.payment

import android.widget.Toast
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
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
fun PaymentScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var selectedMethod by remember { mutableStateOf("Card") }
    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedBottomItem by remember { mutableStateOf("Payment") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = blue1,
                    titleContentColor = Color.White
                ), navigationIcon = {
                    IconButton(onClick = { /* handle drawer/menu */ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)

                    }
                },
            )
        },
        bottomBar = {
            NavigationBar(containerColor = blue1) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedBottomItem == "Home",
                    onClick = {
                        selectedBottomItem = "Home"
                        // navController.navigate("home")
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Payment") },
                    label = { Text("Payment") },
                    selected = selectedBottomItem == "Payment",
                    onClick = {
                        selectedBottomItem = "Payment"
                        // already here
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedBottomItem == "Profile",
                    onClick = {
                        selectedBottomItem = "Profile"
                        // navController.navigate("profile")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            //Animated IMage
            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                visible = true
            }

            val scaleAnim = animateFloatAsState(
                targetValue = if (visible) 1f else 0.8f,
                animationSpec = tween(durationMillis = 800, easing = EaseOutBack),
                label = "scale"
            )

            val alphaAnim = animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = tween(1500),
                label = "alpha"
            )

            Image(
                painter = painterResource(id = R.drawable.img_7),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scaleAnim.value)
                    .alpha(alphaAnim.value),
                contentScale = ContentScale.Crop
            )
            //End

            Spacer(modifier = Modifier.height(32.dp))





            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = blue1
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Payment Method Selection
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedMethod == "Card", onClick = { selectedMethod = "Card" })
                Text("Card", modifier = Modifier
                    .clickable { selectedMethod = "Card" }
                    .padding(end = 16.dp))
                RadioButton(selected = selectedMethod == "M-Pesa", onClick = { selectedMethod = "M-Pesa" })
                Text("M-Pesa", modifier = Modifier.clickable { selectedMethod = "M-Pesa" })
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedMethod == "Card") {
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("Card Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = expiry,
                    onValueChange = { expiry = it },
                    label = { Text("Expiry Date (MM/YY)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    label = { Text("CVV") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("M-Pesa Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val isValid = if (selectedMethod == "Card") {
                        cardNumber.length in 12..19 &&
                                expiry.matches(Regex("\\d{2}/\\d{2}")) &&
                                cvv.length == 3
                    } else {
                        phoneNumber.length == 10 && phoneNumber.startsWith("07")
                    }

                    if (isValid) {
                        if (selectedMethod == "Card") {
                            Toast.makeText(context, "Card payment successful!", Toast.LENGTH_LONG).show()
                        } else {
                            showDialog = true
                        }
                    } else {
                        Toast.makeText(context, "Invalid payment details!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),

                colors = ButtonDefaults.buttonColors(blue1)
            ) {
                Text("Pay Now")
            }
        }

        // M-Pesa Simulation
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Simulate M-Pesa Payment") },
                text = { Text("Confirm M-Pesa payment of Ksh 500 to MobileTrack?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        Toast.makeText(context, "M-Pesa payment simulated!", Toast.LENGTH_LONG).show()
                        // navController.navigate("receipt")
                    }) {
                        Text("Confirm", color = pink)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(rememberNavController())
}
