package com.example.storytales.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    @StringRes label: Int,
    hint: String = "",
    onValueChange: (String) -> Unit,
    isPasswordField: Boolean = false,
    isClickOnly: Boolean = false,
    onClick: () -> Unit = {},
    leadingIcon: ImageVector? = null,
    @StringRes error: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isClickOnly) {
                    onClick()
                }
            },
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            isError = error != null,
            readOnly = isClickOnly,
            enabled = !isClickOnly,
            supportingText = {
                if (error != null) {
                    Text(modifier = Modifier.fillMaxWidth(), text = stringResource(id = error), color = MaterialTheme.colorScheme.error)
                }
            },
            visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
            label = { Text(text = stringResource(id = label))},
            placeholder = { Text(text = hint)},
            leadingIcon = {
                leadingIcon?.let {
                    Icon(imageVector = it, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onDone()
                },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }

}