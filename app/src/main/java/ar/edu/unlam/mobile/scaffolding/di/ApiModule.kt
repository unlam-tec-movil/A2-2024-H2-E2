package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.remote.api.TuitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://tuiter.fragua.com.ar/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTuitApi(retrofit: Retrofit): TuitApi =
        retrofit.create(TuitApi::class.java)
} // TODO: Se deben agregar providers para cada API
