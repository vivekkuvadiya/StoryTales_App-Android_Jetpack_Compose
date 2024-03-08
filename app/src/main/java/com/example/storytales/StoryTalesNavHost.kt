package com.example.storytales

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.storytales.auth.authNavGraph
import com.example.storytales.auth.authRoute

@Composable
fun StoryTalesNavHost(navHostController:NavHostController) {
    NavHost(navController = navHostController, startDestination = authRoute){
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {

            }
        )
    }
}