package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetProfile(profileRepository: ProfileRepository): GetProfile = GetProfile(profileRepository)
}
