package com.example.storytales.theme.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.storytales.theme.StoryTalesTheme

@Composable
fun AppBar(
    title: String,
    navIcon: ImageVector? = null,
    onNav: () -> Unit = {},
) {

    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = onNav) {
                    Icon(imageVector = navIcon, contentDescription = null)
                }
            }
        }
    )

}

@StoryTalesPreview
@Composable
private fun AppBarPreview() {
    StoryTalesTheme {
        AppBar(title = "Home")
    }
}