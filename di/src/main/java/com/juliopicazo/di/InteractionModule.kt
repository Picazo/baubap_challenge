package com.juliopicazo.di

import com.juliopicazo.domain.interactor.LoginUserUseCase
import com.juliopicazo.domain.interactor.LoginUserUseCaseImpl
import com.juliopicazo.domain.repository.BaubapChallengeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InteractionModule {
    @Provides
    fun provideLoginUserUseCase(baubapRepository: BaubapChallengeRepository): LoginUserUseCase = LoginUserUseCaseImpl(baubapRepository)
}
