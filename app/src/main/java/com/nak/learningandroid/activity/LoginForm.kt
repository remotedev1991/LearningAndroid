package com.nak.learningandroid.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nak.learningandroid.viewmodel.LoginViewModel

@Composable
fun LoginForm(modifier: Modifier = Modifier, viewModel: LoginViewModel) {

    val username = viewModel.userName.collectAsStateWithLifecycle()
    val password = viewModel.password.collectAsStateWithLifecycle()

    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Login", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(100.dp))

            Text("Welcome Back", style = MaterialTheme.typography.titleLarge)

            Text(
                "Please enter your details to continue",
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Username",
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username.value,
                onValueChange = { it ->
                    viewModel.updateUserName(it)
                },
                label = {
                    Text("Username")
                }
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Password",
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = {
                    viewModel.updatePassword(it)
                },
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text("Password")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Star, "")
                }
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.login()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Login", modifier = Modifier.padding(vertical = 8.dp)
                )
            }

        }

    }

}

@Preview
@Composable
fun LoginFormPreview() {
    LoginForm(viewModel = LoginViewModel())
}