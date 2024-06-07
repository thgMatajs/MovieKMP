package io.gentalha.code.movie.di

import io.gentalha.code.movie.core.coreModule
import io.gentalha.code.movie.feature.movie_list.di.movieListModule
import io.gentalha.code.movie.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            coreModule,
            movieListModule,
            platformModule()
        )
    }

// called by iOS etc
fun initKoin() = initKoin {}