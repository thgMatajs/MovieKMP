package io.gentalha.code.movie.feature.movie_list.di

import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val movieListModule = module {
    single { MovieRepository(get()) }
    single { MovieService(get()) }
    singleOf<MovieListViewModel>(::MovieListViewModel)
}