package com.example.chatroomapp.data

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatroomapp.screen.LoginScreen
import com.example.chatroomapp.screen.Screen
import com.example.chatroomapp.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import androidx.activity.result.ActivityResultLauncher
import com.example.chatroomapp.screen.ChatRoomListScreen
import com.example.chatroomapp.screen.ChatScreen
import com.example.chatroomapp.screen.SignupScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    googleSignInClient: GoogleSignInClient,
    signInLauncher: ActivityResultLauncher<Intent>
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignupScreen.route
    ) {
        composable(Screen.SignupScreen.route) {
            SignupScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = { navController.navigate(Screen.SignupScreen.route) },
                onSignInSuccess = {
                    navController.navigate(Screen.ChatRoomListScreen.route)
                },
                googleSignInClient = googleSignInClient,
                signInLauncher = signInLauncher
            )
        }
        composable(Screen.ChatRoomListScreen.route) {
            ChatRoomListScreen{
                navController.navigate("${Screen.ChatScreen.route}/${it.id}")
            }
        }
        composable("${Screen.ChatScreen.route}/{roomId}") {
            val roomId : String = it.arguments?.getString("roomId") ?:""
            ChatScreen(roomId = roomId)
        }
    }
}















