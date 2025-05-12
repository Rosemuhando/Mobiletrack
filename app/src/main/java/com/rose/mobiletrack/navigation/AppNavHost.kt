package com.rose.mobiletrack.navigation


import SettingsScreen
import UploadBookingScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.data.BookingDatabase
import com.rose.mobiletrack.data.UserDatabase
import com.rose.mobiletrack.repository.BookingRepository
import com.rose.mobiletrack.repository.UserRepository
import com.rose.mobiletrack.ui.screens.about.AboutScreen
import com.rose.mobiletrack.ui.screens.auth.RegisterScreen
import com.rose.mobiletrack.ui.screens.dashboard.DashboardScreen
import com.rose.mobiletrack.ui.screens.home.HomeScreen
import com.rose.mobiletrack.ui.screens.payment.PaymentScreen
import com.rose.mobiletrack.ui.screens.privacypolicy.PrivacyPolicyScreen
import com.rose.mobiletrack.ui.screens.ridedetails.ContactScreen
import com.rose.mobiletrack.ui.screens.ridedetails.RideDetailsScreen
import com.rose.mobiletrack.ui.screens.splash.SplashScreen
import com.rose.mobiletrack.ui.screens.terms.TermsAndConditionsScreen
import com.rose.mobiletrack.ui.theme.screens.booking.AdminViewBookingScreen
import com.rose.mobiletrack.ui.theme.screens.booking.ViewBookingScreen
import com.rose.mobiletrack.viewmodel.BookingViewModel
import com.rosemuhando.harakamall.ui.screens.auth.LoginScreen
import com.rosemuhando.harakamall.ui.screens.support.SupportScreen
import com.rosemuhando.harakamall.viewmodel.AuthViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH

    ) {

    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }


        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }

        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_RIDER_DETAILS) {
            RideDetailsScreen(navController)
        }
        composable(ROUT_SETTING) {
            SettingsScreen(navController)
        }

        composable(ROUT_PRIVACY_POLICY) {
            PrivacyPolicyScreen(navController)
        }


        composable(ROUT_PAYMENT) {
            PaymentScreen(navController)
        }


        composable(ROUT_SUPPORT) {
            SupportScreen(navController)
        }

        composable(ROUT_TERMS_CONDITIONS) {
            TermsAndConditionsScreen(navController)
        }
        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication

        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }
        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }


        // Initialize Content Database and ViewModel
        val bookingDatabase = BookingDatabase.getDatabase(context)
        val bookingRepository = BookingRepository(bookingDatabase.bookingDao())
        val bookingViewModel = BookingViewModel(bookingRepository)

        composable(ROUT_UPLOAD_BOOKING) {
            UploadBookingScreen(navController, bookingViewModel)
        }
        composable(ROUT_VIEW_BOOKING) {
            ViewBookingScreen(navController, bookingViewModel) { id ->
                navController.navigate("upload_booking?id=$id")
            }
        }

        composable(ROUT_ADMIN_VIEW_BOOKING) {
            AdminViewBookingScreen(navController, bookingViewModel) { id ->
                navController.navigate("upload_booking?id=$id")
            }
        }
//rider history
    }


    }






