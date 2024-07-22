package com.juliopicazo.baubapchallenge

import com.juliopicazo.domain.interactor.LoginUserUseCaseImpl
import com.juliopicazo.domain.model.User
import com.juliopicazo.domain.repository.BaubapChallengeRepository
import com.juliopicazo.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUserUseCaseTest {
    private lateinit var loginUserUseCase: LoginUserUseCaseImpl
    private val repository: BaubapChallengeRepository = mockk()

    @Before
    fun setup() {
        loginUserUseCase = LoginUserUseCaseImpl(repository)
    }

    @Test
    fun `should emit success with login response`() =
        runTest {
            val loginResponse =
                User(
                    status = "success",
                    message = "Login successful",
                    token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
                    userId = "1",
                )

            val expectedFlow =
                flow {
                    emit(Resource.loading(null))
                    emit(Resource.success(loginResponse))
                }

            coEvery { repository.login() } returns expectedFlow

            val result = loginUserUseCase.invoke()

            val results = result.toList()
            assertEquals(2, results.size)
            assertEquals(Resource.loading(null), results[0])
            assertEquals(Resource.success(loginResponse), results[1])
        }
}
