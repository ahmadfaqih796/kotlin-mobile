package com.project.latihan.ui.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.project.latihan.ui.theme.Spacing

@Composable
fun TextFieldV2(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    focusRequester: FocusRequester? = null,
    onDone: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(3.dp, Color.White, RoundedCornerShape(16.dp))
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            modifier = if (focusRequester != null) {
                modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            } else {
                modifier
                    .fillMaxWidth()

            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone?.invoke()
                }
            ),
            isError = isError,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            // colors = colors,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                // underline color
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                // color kedap kedip cursor
                cursorColor = Color.White,
                // error color
                errorContainerColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                errorLabelColor = Color.White,
                errorCursorColor = Color.White,
                errorTextColor = Color.White
            ),
        )
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }

    Spacer(modifier = Modifier.height(Spacing))
}