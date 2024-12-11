package com.netflix.clone.di

import com.netflix.clone.presentation.games.GamesViewModel
import com.netflix.clone.presentation.home.HomeViewModel
import com.netflix.clone.presentation.movie.MovieViewModel
import com.netflix.clone.presentation.news.NewsAndHotViewModel
import com.netflix.clone.presentation.profile.MyNetflixViewModel
import com.netflix.clone.presentation.search.SearchViewModel
import com.netflix.clone.presentation.tv.TvViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModelOf(::GamesViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::MovieViewModel)
        viewModelOf(::MyNetflixViewModel)
        viewModelOf(::NewsAndHotViewModel)
        viewModelOf(::SearchViewModel)
        viewModelOf(::TvViewModel)
    }
