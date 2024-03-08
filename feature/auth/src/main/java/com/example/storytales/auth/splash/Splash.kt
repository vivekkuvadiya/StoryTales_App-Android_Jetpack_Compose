package com.example.storytales.auth.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.storytales.auth.R
import com.example.storytales.theme.StoryTalesTheme
import com.example.storytales.theme.components.StoryTalesPreview

@Composable
fun SplashScreen() {
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
    }
}

@StoryTalesPreview
@Composable
fun SplashPreview() {
    StoryTalesTheme {
        Splash()
    }
}