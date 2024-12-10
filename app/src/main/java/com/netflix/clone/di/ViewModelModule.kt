package com.netflix.clone.di

import com.netflix.clone.presentation.movie.MovieViewModel
import com.netflix.clone.presentation.home.HomeViewModel
import com.netflix.clone.presentation.news.NewsAndHotViewModel
import com.netflix.clone.presentation.profile.MyNetflixViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::NewsAndHotViewModel)
        viewModelOf(::MyNetflixViewModel)
        viewModelOf(::MovieViewModel)
    }
