package com.juliopicazo.baubapchallenge

import com.juliopicazo.baubapchallenge.ui.screen.login.LoginViewModel
import com.juliopicazo.domain.interactor.LoginUserUseCase
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel
    private val getLogin: LoginUserUseCase = mockk()

    @Before
    fun setup() {
        viewModel = LoginViewModel(getLogin)
    }

    @Test
    fun `onEmailUpdated updates email and validates fields`() =
        runTest {
            viewModel.onEmailUpdated("test@test.com")

            val uiState = viewModel.uiState.value
            assertEquals("test@test.com", uiState.emailInput)
            assertFalse(uiState.signInButtonEnabled)
        }

    @Test
    fun `onPasswordUpdated updates password and validates fields`() =
        runTest {
            viewModel.onPasswordUpdated("pass123")

            val uiState = viewModel.uiState.value
            assertEquals("pass123", uiState.passwordInput)
            assertFalse(uiState.signInButtonEnabled)
        }

    @Test
    fun `signInButtonEnabled is true when both email and password are not blank`() =
        runTest {
            viewModel.onEmailUpdated("test@test.com")
            viewModel.onPasswordUpdated("pass123")

            val uiState = viewModel.uiState.value
            assertTrue(uiState.signInButtonEnabled)
        }

    @Test
    fun `onDialogDismissed sets loginSuccess to false`() =
        runTest {
            viewModel.onDialogDismissed()

            val uiState = viewModel.uiState.value
            assertFalse(uiState.loginSuccess)
        }
}
