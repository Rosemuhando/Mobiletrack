package com.rose.mobiletrack.ui.screens.terms

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_PRIVACY_POLICY
import com.rose.mobiletrack.ui.theme.blue1

data class TermsSection(val title: String, val content: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    val context = LocalContext.current
    var isAgreed by remember { mutableStateOf(false) }
    var selectedSection by remember { mutableStateOf<TermsSection?>(null) }

    val termsSections = listOf(
        TermsSection(
            "1. Service Use",
            "You agree to use our transport platform solely for lawful purposes and refrain from any misuse of the app or service."
        ),
        TermsSection(
            "2. User Responsibilities",
            "You are responsible for providing accurate information during bookings and ensuring timely availability at pickup points."
        ),
        TermsSection(
            "3. Payments",
            "All fare calculations are based on distance, and you agree to pay the fee as presented before confirming a booking."
        ),
        TermsSection(
            "4. Cancellation Policy",
            "Cancellations may incur a fee. Please check our cancellation terms in advance to avoid unexpected charges."
        ),
        TermsSection(
            "5. Termination",
            "We reserve the right to suspend or terminate accounts for violations of these terms or for fraudulent activity."
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Terms & Conditions") },
            navigationIcon = {
                IconButton(onClick = { /* Optional menu action */ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = blue1,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp, end = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            termsSections.forEach { section ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { selectedSection = section },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = section.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Tap to read more...",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isAgreed, onCheckedChange = { isAgreed = it })
            Text(text = "I agree to the Terms & Conditions")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (isAgreed) {
                    Toast.makeText(context, "Terms accepted", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_PRIVACY_POLICY)
                } else {
                    Toast.makeText(context, "Please agree to the terms", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(blue1)
        ) {
            Text("Continue")
        }
    }

    // Dialog for section details
    selectedSection?.let { section ->
        AlertDialog(
            onDismissRequest = { selectedSection = null },
            confirmButton = {
                TextButton(onClick = { selectedSection = null }) {
                    Text("Close")
                }
            },
            title = { Text(text = section.title, fontWeight = FontWeight.Bold) },
            text = { Text(text = section.content) }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TermsAndConditionsPreview() {
    TermsAndConditionsScreen(rememberNavController())
}
