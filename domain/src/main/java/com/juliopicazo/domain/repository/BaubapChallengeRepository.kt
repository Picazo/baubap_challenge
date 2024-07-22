package com.juliopicazo.domain.repository

import com.juliopicazo.domain.model.User
import com.juliopicazo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BaubapChallengeRepository {
    suspend fun login(): Flow<Resource<User>>
}
