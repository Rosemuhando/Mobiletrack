package com.rose.mobiletrack.ui.screens.about

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.ui.theme.pink
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_PROFILE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
//Scaffold

    var selectedIndex by remember { mutableStateOf(0) }


    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Contact Screen") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = pink,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        //BottomBar
        bottomBar = {
            NavigationBar(containerColor = pink) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "home") },
                    label = { Text("Search") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                         navController.navigate(ROUT_HOME)
                        //navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )



                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                          navController.navigate(ROUT_PROFILE)

                    }
                )


            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = pink
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.img_2),
                        contentScale = ContentScale.FillBounds
                    ),


                ) {


                //Main Contents of the page
                Text(text = "", fontSize = 20.sp)
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxSize()
                        .paint(
                            painter = painterResource(R.drawable.img_2),
                            contentScale = ContentScale.FillBounds
                        ),

                    )
                Text("")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // App Title
                    Text("MobileTrack", style = MaterialTheme.typography.headlineMedium, )

                    Spacer(modifier = Modifier.height(16.dp))

                    // App Description
                    Text(
                        "MobileTrack is an app that helps you track your rides, drivers, and cars in real time, it also helps riders to save their money because the driver cannot apreciate or deppreciate the fare",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 32.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Version information
                    Text(
                        "Version 1.0.0",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Copyright Information
                    Text(
                        "© 2025 MobileTrack. All rights reserved.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }


        }
    )

    //End of scaffold

}



@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController())
}