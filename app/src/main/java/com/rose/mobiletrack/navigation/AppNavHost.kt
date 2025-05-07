package com.rose.mobiletrack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rose.mobiletrack.data.UserDatabase
import com.rose.mobiletrack.repository.UserRepository
import com.rose.mobiletrack.ui.screens.about.AboutScreen
import com.rose.mobiletrack.ui.screens.auth.RegisterScreen
import com.rose.mobiletrack.ui.screens.dashboard.DashboardScreen
import com.rose.mobiletrack.ui.screens.home.HomeScreen
import com.rose.mobiletrack.ui.screens.payment.PaymentScreen
import com.rose.mobiletrack.ui.screens.privacypolicy.PrivacyPolicyScreen
import com.rose.mobiletrack.ui.screens.profile.ProfileScreen
import com.rose.mobiletrack.ui.screens.rideconfirmation.RiderConfirmationScreen
import com.rose.mobiletrack.ui.screens.ridedetails.ContactScreen
import com.rose.mobiletrack.ui.screens.ridedetails.RideDetailsScreen
import com.rose.mobiletrack.ui.screens.setting.SettingsScreen
import com.rose.mobiletrack.ui.screens.splash.SplashScreen
import com.rosemuhando.harakamall.ui.screens.auth.LoginScreen
import com.rosemuhando.harakamall.ui.screens.support.SupportScreen
import com.rosemuhando.harakamall.viewmodel.AuthViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_DASHBOARD,

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
        composable(ROUT_PAYMENT) {
            PaymentScreen(navController)
        }
        composable(ROUT_PRIVACY_POLICY) {
            PrivacyPolicyScreen(navController)
        }

        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }
        composable(ROUT_SUPPORT) {
            SupportScreen(navController)
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


        /// rider confirmation
        composable(
            "ride_confirmation/{name}/{phone}/{pickup}/{drop}/{distance}/{fare}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("pickup") { type = NavType.StringType },
                navArgument("drop") { type = NavType.StringType },
                navArgument("distance") { type = NavType.FloatType },
                navArgument("fare") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            RiderConfirmationScreen(
                navController = navController,
                name = backStackEntry.arguments?.getString("name") ?: "",
                phone = backStackEntry.arguments?.getString("phone") ?: "",
                pickup = backStackEntry.arguments?.getString("pickup") ?: "",
                drop = backStackEntry.arguments?.getString("drop") ?: "",
                distance = backStackEntry.arguments?.getFloat("distance")?.toDouble() ?: 0.0,
                fare = backStackEntry.arguments?.getInt("fare") ?: 0
            )
        }


//rider history



    }
}





