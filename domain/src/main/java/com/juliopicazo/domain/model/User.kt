package com.juliopicazo.domain.model

import java.io.Serializable

data class User(
    val status: String? = null,
    val message: String? = null,
    val token: String? = null,
    val userId: String? = null,
) : Serializable
