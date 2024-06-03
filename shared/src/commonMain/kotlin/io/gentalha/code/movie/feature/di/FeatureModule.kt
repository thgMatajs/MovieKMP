package io.gentalha.code.movie.feature.di

import io.gentalha.code.movie.feature.movie_list.data.di.movieListModule
import org.koin.dsl.module

val featuresModule = module {
    movieListModule
}