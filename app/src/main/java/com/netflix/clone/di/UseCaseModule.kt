package com.netflix.clone.di

import com.netflix.clone.domain.usecase.GetMovieDetailsUseCase
import com.netflix.clone.domain.usecase.GetNowPlayingMoviesUseCase
import com.netflix.clone.domain.usecase.GetPopularMoviesUseCase
import com.netflix.clone.domain.usecase.GetPopularSeriesUseCase
import com.netflix.clone.domain.usecase.GetSeriesDetailsUseCase
import com.netflix.clone.domain.usecase.GetTopRatedSeriesUseCase
import com.netflix.clone.domain.usecase.GetTrendingUseCase
import com.netflix.clone.domain.usecase.GetUpcomingMoviesUseCase
import com.netflix.clone.domain.usecase.GetUserListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule =
    module {
        singleOf(::GetUserListUseCase)
        singleOf(::GetMovieDetailsUseCase)
        singleOf(::GetNowPlayingMoviesUseCase)
        singleOf(::GetPopularMoviesUseCase)
        singleOf(::GetPopularSeriesUseCase)
        singleOf(::GetSeriesDetailsUseCase)
        singleOf(::GetTopRatedSeriesUseCase)
        singleOf(::GetTrendingUseCase)
        singleOf(::GetUpcomingMoviesUseCase)
    }
