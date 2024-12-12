package com.netflix.clone.di

import com.netflix.clone.core.Constants
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.repository.MovieRepositoryImpl
import com.netflix.clone.domain.repository.MovieRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule =
    module {
        includes(useCaseModule, viewModelModule, databaseModule)
        single<MovieApi> {
            Retrofit
                .Builder()
                .baseUrl(Constants.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
        singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
    }
