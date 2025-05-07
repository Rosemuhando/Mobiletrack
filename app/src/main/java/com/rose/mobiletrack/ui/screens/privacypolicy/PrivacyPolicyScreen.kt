package com.rose.mobiletrack.ui.screens.privacypolicy

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
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.ui.theme.blue1
import com.rose.mobiletrack.ui.theme.pink

data class PolicySection(val title: String, val detail: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val context = LocalContext.current
    var isAgreed by remember { mutableStateOf(false) }
    var selectedPolicy by remember { mutableStateOf<PolicySection?>(null) }

    val policySections = listOf(
        PolicySection(
            "1. Data Collection",
            "We collect your name, phone number, and location strictly to facilitate bookings, calculate distance, and determine fare pricing."
        ),
        PolicySection(
            "2. Data Usage",
            "Your information is used only to enable transportation services. We do not use your data for marketing or analytics without your consent."
        ),
        PolicySection(
            "3. Data Sharing",
            "We do not share your data with third parties unless required by law or for core service delivery like confirming ride availability."
        ),
        PolicySection(
            "4. Data Security",
            "We store all data securely and follow best practices to ensure it’s protected from unauthorized access or breaches."
        ),
        PolicySection(
            "5. Consent",
            "By agreeing, you acknowledge that you have read and understood our privacy practices and voluntarily accept them."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
           ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TopAppBar(
            title = { Text("Privacy Policy") },
            navigationIcon = {
                IconButton(onClick = { /* handle drawer/menu */ }) {
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
            policySections.forEach { policy ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { selectedPolicy = policy },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = policy.title,
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

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isAgreed,
                onCheckedChange = { isAgreed = it }
            )
            Text(text = "I agree to the Privacy Policy")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (isAgreed) {
                    Toast.makeText(context, "Privacy policy accepted", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_HOME)
                } else {
                    Toast.makeText(context, "Please agree to the privacy policy", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),

            colors = ButtonDefaults.buttonColors(blue1)
        ) {
            Text("Continue")
        }
    }

    // Dialog to show selected policy detail
    selectedPolicy?.let { policy ->
        AlertDialog(
            onDismissRequest = { selectedPolicy = null },
            confirmButton = {
                TextButton(onClick = { selectedPolicy = null }) {
                    Text("Close")
                }
            },
            title = {
                Text(text = policy.title, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(text = policy.detail)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(rememberNavController())
}
