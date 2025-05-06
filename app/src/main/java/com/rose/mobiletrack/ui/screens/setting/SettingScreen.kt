package com.rose.mobiletrack.ui.screens.setting

import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.ROUT_ABOUT
import com.rose.mobiletrack.navigation.ROUT_LOGIN
import com.rose.mobiletrack.ui.theme.pink

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(modifier = Modifier
        .fillMaxSize()
        .fillMaxSize()
        .fillMaxSize()
        .paint(painter = painterResource(R.drawable.img_2), contentScale = ContentScale.FillBounds),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = pink,
                    fontSize = (40.sp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        // Notifications toggle
        SettingToggleItem(
            title = "Enable Notifications",
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )

        // Dark mode toggle
        SettingToggleItem(
            title = "Dark Mode",
            checked = darkModeEnabled,
            onCheckedChange = { darkModeEnabled = it }
        )

        // Language selection
        SettingDropdownItem(
            title = "Language",
            selectedOption = selectedLanguage,
            options = listOf("English", "Swahili", "French"),
            onOptionSelected = { selectedLanguage = it }
        )

        // Links
        SettingLinkItem(title = "Terms & Conditions") {
            Toast.makeText(context, "Opening Terms & Conditions...", Toast.LENGTH_SHORT).show()
        }

        SettingLinkItem(title = "Privacy Policy") {
            Toast.makeText(context, "Opening Privacy Policy...", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()

                    navController.navigate(ROUT_LOGIN)

                // Navigate to login or home
                // navController.navigate("login") { popUpTo("settings") { inclusive = true } }
            },
            colors = ButtonDefaults.buttonColors(pink),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log Out")
        }
    }
}

@Composable
fun SettingToggleItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun SettingDropdownItem(
    title: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)) {
        Text(text = title)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = selectedOption)
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SettingLinkItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Icon(Icons.Default.ArrowForward, contentDescription = null)
    }
}
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())

}