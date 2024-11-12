package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.DraftTuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.LocalProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.GetDraftTuits
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.RemoveDraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation.SaveDraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth.LoginUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth.RegisterUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.GetFavoriteUsers
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.RemoveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite.SaveFavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetLocalProfile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetProfile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.SaveLocalProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideLoginUser(authRepository: AuthRepository): LoginUser = LoginUser(authRepository)

    @Provides
    fun provideRegisterUser(authRepository: AuthRepository): RegisterUser = RegisterUser(authRepository)

    @Provides
    fun provideGetProfile(profileRepository: ProfileRepository): GetProfile = GetProfile(profileRepository)

    @Provides
    fun provideSaveFavoriteUser(favoriteUserRepository: FavoriteUserRepository): SaveFavoriteUser = SaveFavoriteUser(favoriteUserRepository)

    @Provides
    fun provideRemoveFavoriteUser(favoriteUserRepository: FavoriteUserRepository): RemoveFavoriteUser =
        RemoveFavoriteUser(favoriteUserRepository)

    @Provides
    fun provideGetFavoriteUser(favoriteUserRepository: FavoriteUserRepository): GetFavoriteUsers = GetFavoriteUsers(favoriteUserRepository)

    @Provides
    fun provideGetDraftTuit(draftTuitRepository: DraftTuitRepository): GetDraftTuits = GetDraftTuits(draftTuitRepository)

    @Provides
    fun provideSaveDraftTuit(draftTuitRepository: DraftTuitRepository): SaveDraftTuit = SaveDraftTuit(draftTuitRepository)

    @Provides
    fun provideRemoveDraftTuit(draftTuitRepository: DraftTuitRepository): RemoveDraftTuit = RemoveDraftTuit(draftTuitRepository)

    @Provides
    fun provideGetLocalProfile(localProfileRepository: LocalProfileRepository): GetLocalProfile = GetLocalProfile(localProfileRepository)

    @Provides
    fun provideSaveLocalProfile(localProfileRepository: LocalProfileRepository): SaveLocalProfile = SaveLocalProfile(localProfileRepository)
}
