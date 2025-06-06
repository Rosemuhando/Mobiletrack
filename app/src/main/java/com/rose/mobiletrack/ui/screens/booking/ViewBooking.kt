package com.rose.mobiletrack.ui.theme.screens.booking


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rose.mobiletrack.R
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_PAYMENT
import com.rose.mobiletrack.navigation.ROUT_SETTING
import com.rose.mobiletrack.navigation.ROUT_UPLOAD_BOOKING
import com.rose.mobiletrack.navigation.ROUT_VIEW_BOOKING
import com.rose.mobiletrack.ui.theme.blue1
import com.rose.mobiletrack.ui.theme.grey
import com.rose.mobiletrack.viewmodel.BookingViewModel

import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ViewBookingScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel,
    onEdit: (Int) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val contentList by bookingViewModel.allBooking.collectAsState(initial = emptyList())

    val carouselImages = listOf(R.drawable.car, R.drawable.img_1, R.drawable.img)
    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentImageIndex = (currentImageIndex + 1) % carouselImages.size
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            NavigationBar(containerColor = blue1){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "home") },
                    label = { Text("Search") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                        //navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Favorites") },
                    label = { Text("settings") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_SETTING)}

                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Profile") },
                    label = { Text("payment") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate(ROUT_PAYMENT)

                    }
                )



            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ROUT_UPLOAD_BOOKING) },
                containerColor = blue1
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .fillMaxSize()
        ) {

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Card (
                    modifier = Modifier
                        .size(70.dp),
                    shape = RoundedCornerShape(50)
                ){

                }
            }
            Text(
                text = "View",
                fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row (
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { navController.navigate(ROUT_VIEW_BOOKING) },
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                    colors = ButtonDefaults.buttonColors(blue1)
                ) {
                    Text(
                        text = "View",
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = { navController.navigate(ROUT_UPLOAD_BOOKING) },
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                    colors = ButtonDefaults.buttonColors(blue1),
                ) {
                    Text(
                        text = "Add Booking",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                items(contentList.size) { index ->
                    val booking = contentList[index]
                    val backgroundColor = if (index % 2 == 0) Color(0xFFD5E9EC)else Color(0xFFD5E9EC)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(Color.LightGray),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(5.dp))
                                Text(text = "Name: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.name, fontSize = 10.sp)
                            }


                            Spacer(Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(5.dp))
                                Text(text = "Description: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.description, fontSize = 10.sp, maxLines = 2)
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(5.dp))
                                Text(text = "Phone: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.phone, fontSize = 10.sp)
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Pickup: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.pickup, fontSize = 10.sp)
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(5.dp))
                                Text(text = "Drop: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.drop, fontSize = 10.sp)
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(Modifier.width(5.dp))
                                Text(text = "Date & Time: ", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = booking.date, fontSize = 10.sp)
                            }
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.End
                            ) {


                                }
                            }
                        }
                    }
                }
            }
        }
    }

