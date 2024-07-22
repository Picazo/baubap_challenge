package com.juliopicazo.baubapchallenge.ui.screen.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.juliopicazo.baubapchallenge.R

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = uiState,
        onEmailInputUpdated = viewModel::onEmailUpdated,
        onPasswordInputUpdated = viewModel::onPasswordUpdated,
        onSignInPressed = viewModel::onSignInPressed,
        onDialogDismissed = viewModel::onDialogDismissed,
    )
}

@Composable
fun LoginScreenContent(
    uiState: LoginUIState,
    onEmailInputUpdated: (String) -> Unit,
    onPasswordInputUpdated: (String) -> Unit,
    onSignInPressed: () -> Unit,
    onDialogDismissed: () -> Unit,
) {
    if (uiState.loginSuccess) {
        SuccessDialog(onDismiss = onDialogDismissed)
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        if (uiState.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            Body(
                Modifier.align(Alignment.Center),
                uiState = uiState,
                onEmailInputUpdated = onEmailInputUpdated,
                onPasswordInputUpdated = onPasswordInputUpdated,
                onSignInPressed = onSignInPressed,
            )
        }
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFF631bdb))
                .height(1.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.signup_prompt),
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5),
        )
        Text(
            text = stringResource(id = R.string.signup_action),
            Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF641CDC),
        )
    }
}

@Composable
fun Body(
    modifier: Modifier,
    uiState: LoginUIState,
    onEmailInputUpdated: (String) -> Unit,
    onPasswordInputUpdated: (String) -> Unit,
    onSignInPressed: () -> Unit,
) {
    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(uiState.emailInput, onEmailInputUpdated)
        Spacer(modifier = Modifier.size(4.dp))
        Password(uiState.passwordInput, onPasswordInputUpdated)
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(uiState.signInButtonEnabled, onSignInPressed)
    }
}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    onSignInPressed: () -> Unit,
) {
    Button(
        onClick = onSignInPressed,
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors =
        ButtonDefaults.buttonColors(
            containerColor = Color(0xFF641CDC),
            disabledContainerColor = Color(0xff8c8c8c),
            contentColor = Color.White,
            disabledContentColor = Color.White,
        ),
    ) {
        Text(
            text = stringResource(id = R.string.login_button),
            color = Color.White,
        )
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.forgot_password),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF641CDC),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    password: String,
    onTextChanged: (String) -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = {
            onTextChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(stringResource(id = R.string.password_placeholder)) },
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "show password")
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    email: String,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = {
            onTextChanged(it)
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = R.string.email_placeholder)) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF000000),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_baubap),
        contentDescription = stringResource(id = R.string.logo_description),
        modifier = modifier,
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(id = R.string.close_app_description),
        modifier = modifier.clickable { activity.finish() },
    )
}

@Composable
fun SuccessDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(
                    stringResource(id = R.string.dialog_btn_confirm),
                    color = Color.White,
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.dialog_title))
        },
        text = {
            Text(stringResource(id = R.string.dialog_message))
        },
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
