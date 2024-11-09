package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.remote.api.ProfileApi
import ar.edu.unlam.mobile.scaffolding.data.remote.api.TuitApi
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.ProfileMapper
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.TuitMapper
import ar.edu.unlam.mobile.scaffolding.data.repository.RemoteProfileRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.RemoteTuitRepository
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
    fun provideProfileRepository(
        api: ProfileApi,
        mapper: ProfileMapper,
    ): ProfileRepository = RemoteProfileRepository(api, mapper)
}
