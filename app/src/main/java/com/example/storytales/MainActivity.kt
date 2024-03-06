package com.example.storytales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.storytales.theme.StoryTalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoryTalesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Story Tales")
                }
            }
        }

    }
}
