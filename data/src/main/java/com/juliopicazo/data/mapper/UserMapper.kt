package com.juliopicazo.data.mapper

import com.juliopicazo.data.networking.model.response.LoginResponse
import com.juliopicazo.domain.model.User

fun LoginResponse.toDomain() =
    User(
        status = status,
        message = message,
        token = token,
        userId = userId,
    )
