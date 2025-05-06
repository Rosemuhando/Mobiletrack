package com.rose.mobiletrack.ui.screens.payment

import androidx.compose.foundation.clickable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.ui.theme.pink

@Composable
fun PaymentScreen(navController: NavController) {
    val context = LocalContext.current

    // State variables
    var selectedMethod by remember { mutableStateOf("Card") }
    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        Text("Payment Method", style = MaterialTheme.typography.headlineSmall,fontWeight = FontWeight.ExtraBold,color = pink, fontSize = (40.sp))

        Spacer(modifier = Modifier.height(16.dp))

        // Payment Method Selection
        Row {
            RadioButton(
                selected = selectedMethod == "Card",
                onClick = { selectedMethod = "Card" }
            )
            Text("Card", modifier = Modifier
                .padding(end = 16.dp)
                .clickable { selectedMethod = "Card" })

            RadioButton(
                selected = selectedMethod == "M-Pesa",
                onClick = { selectedMethod = "M-Pesa" }
            )
            Text("M-Pesa", modifier = Modifier
                .clickable { selectedMethod = "M-Pesa" })
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
        } else if (selectedMethod == "M-Pesa") {
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
                    cardNumber.length >= 12 && cvv.length == 3 && expiry.contains("/")
                } else {
                    phoneNumber.length == 10 && phoneNumber.startsWith("07")
                }

                if (isValid) {
                    Toast.makeText(context, "Payment Successful", Toast.LENGTH_SHORT).show()
                    // navController.navigate("confirmation" or "receipt")
                } else {
                    Toast.makeText(context, "Please enter valid payment details", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(pink),
        ) {
            Text("Pay Now")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview(){
    PaymentScreen(navController= rememberNavController())
}