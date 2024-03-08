package com.example.storytales.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.storytales.auth.R
import com.example.storytales.theme.StoryTalesTheme
import com.example.storytales.theme.components.AppTextField
import com.example.storytales.theme.components.StoryTalesPreview

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Login(uiState = uiState.value) {
        viewModel.onEvent(it)
    }
}

@Composable
fun Login(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 42.dp, bottom = 12.dp)
                .width(150.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )

        AppTextField(
            value = uiState.email,
            hint = "yourname@domain.com",
            leadingIcon = Icons.Filled.Email,
            imeAction = ImeAction.Next,
            label = R.string.email,
            onValueChange = { onEvent(LoginUiEvent.EmailChanged(it)) }
        )

        AppTextField(
            value = uiState.password,
            label = R.string.password,
            leadingIcon = Icons.Filled.Lock,
            imeAction = ImeAction.Done,
            isPasswordField = true,
            onValueChange = { onEvent(LoginUiEvent.PasswordChanges(it)) }
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(id = R.string.click_hear_to_reset),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp), onClick = { }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null)
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
                .clickable { },
            text = stringResource(id = R.string.dont_have_account),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            text = stringResource(id = R.string.agree_to_terms),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )

    }


}

@StoryTalesPreview
@Composable
fun LoginPreview() {
    StoryTalesTheme {
//        Login()
    }
}
