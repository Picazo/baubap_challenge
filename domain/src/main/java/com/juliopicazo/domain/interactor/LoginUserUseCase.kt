package com.juliopicazo.domain.interactor

import com.juliopicazo.domain.model.User
import com.juliopicazo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginUserUseCase {
    suspend operator fun invoke(): Flow<Resource<User>>
}
