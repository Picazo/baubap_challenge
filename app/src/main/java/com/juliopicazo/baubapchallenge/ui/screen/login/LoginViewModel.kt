package com.juliopicazo.baubapchallenge.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliopicazo.baubapchallenge.utils.isValidEmail
import com.juliopicazo.domain.interactor.LoginUserUseCase
import com.juliopicazo.domain.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val getLogin: LoginUserUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()

    fun onEmailUpdated(email: String) {
        _uiState.update { it.copy(emailInput = email) }
        validateFields()
    }

    fun onPasswordUpdated(password: String) {
        _uiState.update { it.copy(passwordInput = password) }
        validateFields()
    }

    private fun validateFields() {
        _uiState.update {
            it.copy(signInButtonEnabled = (it.emailInput.isNotBlank() && it.emailInput.isValidEmail()) && it.passwordInput.isNotBlank())
        }
    }

    fun onSignInPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }

            getLogin.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        _uiState.update { it.copy(isLoading = false, loginSuccess = true) }
                    }
                    Status.LOADING -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun onDialogDismissed() {
        _uiState.update { it.copy(loginSuccess = false) }
    }
}
