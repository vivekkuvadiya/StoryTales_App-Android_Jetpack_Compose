package com.example.storytales.auth

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.storytales.auth.login.LoginScreen
import com.example.storytales.auth.splash.SplashScreen

const val authRoute = "auth"

sealed class AuthScreen(val route:String){
    object Splash:AuthScreen("$authRoute/splash")
    object Login:AuthScreen("$authRoute/login")
    object Signup:AuthScreen("$authRoute/signup")

}

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onAuthSuccess:()->Unit,
){
    navigation(startDestination = AuthScreen.Splash.route ,route = authRoute){
        composable(AuthScreen.Splash.route){
            LoginScreen(viewModel())
        }
        composable(AuthScreen.Login.route){
            LoginScreen(viewModel())
        }
    }
}