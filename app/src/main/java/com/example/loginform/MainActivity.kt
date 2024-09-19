package com.example.loginform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginform.ui.theme.LoginFormTheme
import androidx.compose.ui.text.input.PasswordVisualTransformation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginFormTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginFormTheme {
        Login()
    }
}

@Composable
fun Login() {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var usernameHasFocus by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }
    var passwordHasFocus by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.Title),
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),

        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.user)) },
            singleLine = true,
            isError = !isEmailValid && !usernameHasFocus,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            trailingIcon = { Icon(Icons.Outlined.AccountCircle, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        if (username.isNotEmpty()) {
                            isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
                        }
                        usernameHasFocus = false
                    } else {
                        usernameHasFocus = true
                    }
                }
        )
        if (!isEmailValid && !usernameHasFocus) {
            Text(
                text = stringResource(R.string.invalid_email),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.pass)) },
            singleLine = true,
            isError = !isPasswordValid && password.isNotEmpty() && !passwordHasFocus,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        isPasswordValid = password.length >= 8
                        passwordHasFocus = false
                    } else {
                        passwordHasFocus = true
                    }
                }
        )
        if (!isPasswordValid && password.isNotEmpty() && !passwordHasFocus) {
            Text(
                text = stringResource(R.string.invalid_password),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Button(
            onClick = {
                // Tähän ei nyt mittään
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.submit))
        }
    }
}