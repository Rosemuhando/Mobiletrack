import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rose.mobiletrack.model.Booking
import com.rose.mobiletrack.navigation.ROUT_DASHBOARD
import com.rose.mobiletrack.navigation.ROUT_HOME
import com.rose.mobiletrack.navigation.ROUT_PAYMENT
import com.rose.mobiletrack.navigation.ROUT_SUPPORT
import com.rose.mobiletrack.navigation.ROUT_VIEW_BOOKING
import com.rose.mobiletrack.ui.theme.blue1
import com.rose.mobiletrack.viewmodel.BookingViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadBookingScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel,
    editingBookingId: Int? = null
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Bookings") },
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
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("support") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_SUPPORT)
                    }
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
                onClick = { navController.navigate(ROUT_DASHBOARD)/* Add action */ },
                containerColor = blue1
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                val context = LocalContext.current

                var name by remember { mutableStateOf("") }
                var phone by remember { mutableStateOf("") }
                var drop by remember { mutableStateOf("") }
                var pickup by remember { mutableStateOf("") }
                var description by remember { mutableStateOf("") }
                var date by remember { mutableStateOf("") }

                LaunchedEffect(editingBookingId) {
                    if (editingBookingId != null) {
                        bookingViewModel.loadBookingById(editingBookingId)
                    }
                }

                val editingBooking by bookingViewModel.selectedBooking.collectAsState()

                LaunchedEffect(editingBooking) {
                    editingBooking?.let {
                        name = it.name
                        phone = it.phone
                        drop = it.drop
                        pickup = it.pickup
                        date = it.date
                        description = it.description
                    }
                }

                Column(Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Dropdown
                    var mExpanded by remember { mutableStateOf(false) }
                    val options = listOf("Pricing", "Booking", "Tracking")
                    var mTextFieldSize by remember { mutableStateOf(IntSize(0, 0)) }


                    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

                    Column {
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    mTextFieldSize = coordinates.size // Using IntSize from Compose
                                },
                            label = { Text("Choose task category") },
                            trailingIcon = {
                                Icon(icon, contentDescription = null, Modifier.clickable { mExpanded = !mExpanded })
                            }
                        )
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false },


                            // Corrected size conversion
                        ) {
                            options.forEach { label ->
                                DropdownMenuItem(
                                    text = { Text(text = label) },
                                    onClick = {
                                        phone = label
                                        mExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = pickup,
                        onValueChange = { pickup = it },
                        label = { Text("Pickup Location") },
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(Modifier.height(8.dp))

                    OutlinedTextField(
                        value = drop,
                        onValueChange = {drop= it },
                        label = { Text("Dropoff Location") },
                        modifier = Modifier.fillMaxWidth()
                    )



                    Spacer(modifier = Modifier.height(8.dp))

                    // Date & Time Picker
                    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Button(
                            onClick = {
                                val calendar = Calendar.getInstance()
                                val year = calendar.get(Calendar.YEAR)
                                val month = calendar.get(Calendar.MONTH)
                                val day = calendar.get(Calendar.DAY_OF_MONTH)

                                android.app.DatePickerDialog(
                                    context,
                                    { _, selectedYear, selectedMonth, selectedDay ->
                                        val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                                        val hour = calendar.get(Calendar.HOUR_OF_DAY)
                                        val minute = calendar.get(Calendar.MINUTE)

                                        android.app.TimePickerDialog(
                                            context,
                                            { _, selectedHour, selectedMinute ->
                                                val selectedTime = "%02d:%02d".format(selectedHour, selectedMinute)
                                                date = "$selectedDate $selectedTime"
                                            },
                                            hour,
                                            minute,
                                            true
                                        ).show()
                                    },
                                    year,
                                    month,
                                    day
                                ).show()
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(blue1),
                            modifier = Modifier.height(65.dp)
                        ) {
                            Text("Date & Time")
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        OutlinedTextField(
                            value = date,
                            onValueChange = {},
                            label = { Text("Selected") },
                            readOnly = true,
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .width(250.dp),
                            trailingIcon = { Text("📅") },
                            singleLine = true
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val booking = Booking(
                                id = editingBooking?.id ?: 0,
                                name = name,
                                phone = phone,
                                pickup = pickup,
                                drop = drop,
                                date = date,
                                description = description
                            )
                            if (editingBooking != null) {
                                bookingViewModel.update(booking)
                            } else {
                                bookingViewModel.insert(booking)
                            }
                            navController.navigate(ROUT_VIEW_BOOKING)
                        },
                        modifier = Modifier,

                        colors = ButtonDefaults.buttonColors(blue1),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(if (editingBooking != null) "Update Booking" else "Upload Booking")
                    }
                }
            }
        }
    )
}

