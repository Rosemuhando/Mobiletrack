package com.rose.mobiletrack.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.*
import com.rose.mobiletrack.ui.theme.blue1
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    val navItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, ROUT_HOME),
        BottomNavItem("Profile", Icons.Default.Person, ROUT_PROFILE),
        BottomNavItem("Settings", Icons.Default.Settings, ROUT_SETTING)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MobileTrack Dashboard") },
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
        },
        bottomBar = {
            NavigationBar(containerColor = blue1) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { padding ->
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Welcome Back!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = blue1,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                DashboardGrid(navController = navController)

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = blue1)
                ) {
                    Text(
                        text = "Tip: You can track your rider in real-time and view trip history at any time.",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardGrid(navController: NavController) {
    val items = listOf(
        Triple("Home", R.drawable.home, ROUT_HOME),
        Triple("About", R.drawable.about, ROUT_ABOUT),
        Triple("Contact", R.drawable.contact, ROUT_CONTACT),
        Triple("Payment", R.drawable.payment, ROUT_PAYMENT),
        Triple("Settings", R.drawable.settings, ROUT_SETTING),
        Triple("Support", R.drawable.support, ROUT_SUPPORT),
        Triple("Profile", R.drawable.profile1, ROUT_PROFILE),
        Triple("Details", R.drawable.details, ROUT_RIDER_DETAILS)
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        for (chunk in items.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for ((label, icon, route) in chunk) {
                    DashboardCard(
                        label = label,
                        iconRes = icon,
                        onClick = {
                            navController.navigate(route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )

                }
            }
        }
    }
}

@Composable
fun DashboardCard(label: String, iconRes: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = modifier
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
}
