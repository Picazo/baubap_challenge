package com.juliopicazo.baubapchallenge.ui.screen.login

import com.juliopicazo.baubapchallenge.utils.Constants.EMPTY_STRING

data class LoginUIState(
    var emailInput: String = EMPTY_STRING,
    var passwordInput: String = EMPTY_STRING,
    var signInButtonEnabled: Boolean = false,
    var loginSuccess: Boolean = false,
    val isLoading: Boolean = false,
)
