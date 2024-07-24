package com.example.chatroomapp.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatroomapp.screen.LoginScreen
import com.example.chatroomapp.screen.Screen
import com.example.chatroomapp.screen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) }
            )
        }
    }
}