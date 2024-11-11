package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.local.dao.FavoriteUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.FavoriteUserMapper
import ar.edu.unlam.mobile.scaffolding.data.remote.api.AuthApi
import ar.edu.unlam.mobile.scaffolding.data.remote.api.ProfileApi
import ar.edu.unlam.mobile.scaffolding.data.remote.api.TuitApi
import ar.edu.unlam.mobile.scaffolding.data.remote.interceptor.TokenManager
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.AuthMapper
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.ProfileMapper
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.TuitMapper
import ar.edu.unlam.mobile.scaffolding.data.repository.AuthenticationRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.LocalDatabaseFavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.RemoteProfileRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.RemoteTuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTuitRepository(
        api: TuitApi,
        mapper: TuitMapper,
    ): TuitRepository = RemoteTuitRepository(api, mapper)

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        mapper: AuthMapper,
        tokenManager: TokenManager,
    ): AuthRepository = AuthenticationRepository(api, mapper, tokenManager)

    @Provides
    @Singleton
    fun provideProfileRepository(
        api: ProfileApi,
        mapper: ProfileMapper,
    ): ProfileRepository = RemoteProfileRepository(api, mapper)

    @Provides
    @Singleton
    fun provideFavoriteUserRepository(
        dao: FavoriteUserDao,
        mapper: FavoriteUserMapper,
    ): FavoriteUserRepository = LocalDatabaseFavoriteUserRepository(dao, mapper)
}
