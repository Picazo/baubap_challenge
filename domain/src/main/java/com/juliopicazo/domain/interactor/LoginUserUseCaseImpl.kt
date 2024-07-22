package com.juliopicazo.domain.interactor

import com.juliopicazo.domain.repository.BaubapChallengeRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserUseCaseImpl
    @Inject
    constructor(
        private val baubapChallengeRepository: BaubapChallengeRepository,
    ) : LoginUserUseCase {
        override suspend operator fun invoke() =
            flow {
                emitAll(baubapChallengeRepository.login())
            }
    }
