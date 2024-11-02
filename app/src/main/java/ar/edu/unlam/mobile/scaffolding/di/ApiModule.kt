package ar.edu.unlam.mobile.scaffolding.di

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
            .baseUrl("https://tuiter-back-xcdb34ok6q-tl.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}

// TODO: Se deben agregar providers para cada API

