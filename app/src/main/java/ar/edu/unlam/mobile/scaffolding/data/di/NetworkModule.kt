package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.data.network.TuitApiService
import ar.edu.unlam.mobile.scaffolding.data.repository.TuitRepositoryImpl
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class NetworkModule {
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://tuiter-back-xcdb34ok6q-tl.a.run.app/") // URL base actualizada
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun provideTuitApiService(retrofit: Retrofit): TuitApiService = retrofit.create(TuitApiService::class.java)

    fun provideTuitRepository(tuitApiService: TuitApiService): TuitRepository = TuitRepositoryImpl(tuitApiService)
}
