package com.rose.mobiletrack.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rose.mobiletrack.data.UserDatabase
import com.rose.mobiletrack.repository.UserRepository
import com.rose.mobiletrack.ui.screens.about.AboutScreen
import com.rose.mobiletrack.ui.screens.contact.ContactScreen
import com.rose.mobiletrack.ui.screens.dashboard.DashboardScreen
import com.rose.mobiletrack.ui.screens.form.FormScreen
import com.rose.mobiletrack.ui.screens.home.HomeScreen
import com.rose.mobiletrack.ui.screens.service.ServiceScreen
import com.rose.mobiletrack.ui.screens.splash.SplashScreen
import com.rose.mobiletrack.ui.screens.start.StartScreen
import com.rose.mobiletrack.viewmodel.AuthViewModel
import com.rosemuhando.harakamall.ui.screens.auth.LoginScreen
import com.rosemuhando.harakamall.ui.screens.auth.RegisterScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,

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
        composable(ROUT_START) {
            StartScreen(navController)
        }
        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }
        composable(ROUT_FORM) {
            FormScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
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

    }
}

