package com.dsm.firebaseauth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dsm.firebaseauth.presentacion.initial.InitialScreen
import com.dsm.firebaseauth.presentacion.login.LoginScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth
) {
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                navigateToLogin={navHostController.navigate("logIn")},
                navigateToSignUp={navHostController.navigate("signUp")}
            )
        }

        composable("logIn") {
            LoginScreen(
                auth,
                navigateToSignUp={navHostController.navigate("signUp")}
            )

        }

        composable("signUp") {
            // SignUpScreen()
        }

        composable("home") {
            // HomeScreen()
        }
    }
}
