package com.netflix.clone.di

import com.netflix.clone.data.repository.MovieDetailsLocalRepositoryImpl
import com.netflix.clone.data.repository.MovieRepositoryImpl
import com.netflix.clone.data.repository.TrendingLocalRepositoryImpl
import com.netflix.clone.domain.repository.MovieDetailsLocalRepository
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.TrendingLocalRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule =
    module {
        singleOf(::MovieDetailsLocalRepositoryImpl) { bind<MovieDetailsLocalRepository>() }
        singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
        singleOf(::TrendingLocalRepositoryImpl) { bind<TrendingLocalRepository>() }
    }
