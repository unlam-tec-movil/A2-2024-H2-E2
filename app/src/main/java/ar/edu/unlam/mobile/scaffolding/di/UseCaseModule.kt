package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideSaveFavoriteUser(favoriteUserRepository: FavoriteUserRepository): SaveFavoriteUser {
        return SaveFavoriteUser(favoriteUserRepository)
    }

    @Provides
    fun provideRemoveFavoriteUser(favoriteUserRepository: FavoriteUserRepository): RemoveFavoriteUser {
        return RemoveFavoriteUser(favoriteUserRepository)
    }

    @Provides
    fun provideGetFavoriteUsers(favoriteUserRepository: FavoriteUserRepository): GetFavoriteUsers {
        return GetFavoriteUsers(favoriteUserRepository)
    }
}
