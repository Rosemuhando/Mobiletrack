package com.rose.mobiletrack.ui.theme.screens.booking


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.rose.mobiletrack.navigation.ROUT_UPLOAD_BOOKING
import com.rose.mobiletrack.navigation.ROUT_VIEW_BOOKING
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

    val carouselImages = listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_2)
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
                    containerColor = Color.LightGray,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        bottomBar = {
            NavigationBar(containerColor = Color.LightGray) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ROUT_UPLOAD_BOOKING) },
                containerColor = Color.LightGray
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
                text = "View Bookings",
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
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "View Bookings",
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = { navController.navigate(ROUT_UPLOAD_BOOKING) },
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Gray),
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
                    val backgroundColor = if (index % 2 == 0) Color(0xFFEDEDE2) else Color(0xFFD5E9EC)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(containerColor = backgroundColor),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.name1), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Name: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.name, fontSize = 16.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.name1), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Description: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.description, fontSize = 16.sp, maxLines = 2)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.formatlistnumbered), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Phone: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.phone, fontSize = 16.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.event), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Pickup: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.pickup, fontSize = 16.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.event), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Drop: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.drop, fontSize = 16.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.event), contentDescription = "", tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "Date & Time: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(text = booking.date, fontSize = 16.sp)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = { onEdit(booking.id) }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Blue)
                                }
                                IconButton(onClick = { bookingViewModel.delete(booking) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
