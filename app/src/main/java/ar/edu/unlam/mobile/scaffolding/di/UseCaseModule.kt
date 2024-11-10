package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
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

    @Provides
    fun provideSaveFavoriteUser(
        favoriteUserRepository: FavoriteUserRepository
    ): SaveFavoriteUser = SaveFavoriteUser(favoriteUserRepository)

    @Provides
    fun provideRemoveFavoriteUser(
        favoriteUserRepository: FavoriteUserRepository
    ): RemoveFavoriteUser = RemoveFavoriteUser(favoriteUserRepository)

    @Provides
    fun provideGetFavoriteUser(
        favoriteUserRepository: FavoriteUserRepository
    ): GetFavoriteUsers = GetFavoriteUsers(favoriteUserRepository)
}
