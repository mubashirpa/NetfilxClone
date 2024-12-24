package com.netflix.clone.di

import com.netflix.clone.core.Constants
import com.netflix.clone.data.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule =
    module {
        includes(useCaseModule, viewModelModule, databaseModule, repositoryModule)
        single<MovieApi> {
            Retrofit
                .Builder()
                .baseUrl(Constants.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
