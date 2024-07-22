package com.juliopicazo.di

import com.juliopicazo.data.networking.BaubapChallengeApi
import com.juliopicazo.data.repository.BaubapRepositoryImpl
import com.juliopicazo.domain.repository.BaubapChallengeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTvShowRepository(baubapChallengeApi: BaubapChallengeApi): BaubapChallengeRepository =
        BaubapRepositoryImpl(baubapChallengeApi)
}
