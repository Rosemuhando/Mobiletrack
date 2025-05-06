package com.rose.mobiletrack.ui.screens.privacypolicy

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_LOGIN
import com.rose.mobiletrack.ui.theme.pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val context = LocalContext.current
    var isAgreed by remember { mutableStateOf(false) }

    val privacyPolicyText = """
        Privacy Policy

        This transportation app collects your name, phone number, and location to facilitate trip booking and pricing.
        Your data is used only for providing services and is not shared with third parties without your consent.

        We take your privacy seriously and store your data securely.

        By continuing, you agree to this policy.
    """.trimIndent()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "Privacy Policy",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(bottom = 8.dp),
                    fontWeight = FontWeight.ExtraBold,
            color = pink,
            fontSize = (40.sp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = privacyPolicyText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isAgreed,
                onCheckedChange = { isAgreed = it }
            )
            Text(text = "I agree to the Privacy Policy")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                if (isAgreed) {
                    Toast.makeText(context, "Privacy policy accepted", Toast.LENGTH_SHORT).show()
                    // Navigate to next screen here (using NavController if available)
                    navController.navigate(ROUT_HOME)
                } else {
                    Toast.makeText(context, "Please agree to the privacy policy", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(pink),
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen(rememberNavController())
}
