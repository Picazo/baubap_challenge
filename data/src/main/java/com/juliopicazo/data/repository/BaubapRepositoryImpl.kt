package com.juliopicazo.data.repository

import android.util.Log
import com.juliopicazo.data.mapper.toDomain
import com.juliopicazo.data.networking.BaubapChallengeApi
import com.juliopicazo.domain.model.User
import com.juliopicazo.domain.repository.BaubapChallengeRepository
import com.juliopicazo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BaubapRepositoryImpl
    @Inject
    constructor(
        private val baubapApi: BaubapChallengeApi,
    ) : BaubapChallengeRepository {
        override suspend fun login(): Flow<Resource<User>> =
            flow {
                emit(Resource.loading())
                try {
                    val response =
                        baubapApi.login()
                    if (response.isSuccessful) {
                        response.body()?.toDomain().let {
                            emit(Resource.success(it))
                        }
                    } else {
                        emit(Resource.success(User()))
                    }
                } catch (e: Throwable) {
                    Log.d("Error", e.message.toString())
                }
            }
    }
