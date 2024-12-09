package com.netflix.clone.di

import com.netflix.clone.domain.usecase.GetListUseCase
import com.netflix.clone.domain.usecase.movie.GetPopularMoviesUseCase
import com.netflix.clone.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.netflix.clone.domain.usecase.series.GetPopularSeriesUseCase
import com.netflix.clone.domain.usecase.trending.GetTrendingUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule =
    module {
        singleOf(::GetPopularMoviesUseCase)
        singleOf(::GetTrendingUseCase)
        singleOf(::GetPopularSeriesUseCase)
        singleOf(::GetUpcomingMoviesUseCase)
        singleOf(::GetListUseCase)
    }
