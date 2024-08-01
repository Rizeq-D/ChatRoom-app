package com.example.chatroomapp.screen

sealed class Screen(val route:String){
    object LoginScreen:Screen("loginscreen")
    object SignupScreen:Screen("signupscreen")
    object ChatRoomListScreen:Screen("chatroomlistscreen")
    object ChatScreen:Screen("chatscreen")
}