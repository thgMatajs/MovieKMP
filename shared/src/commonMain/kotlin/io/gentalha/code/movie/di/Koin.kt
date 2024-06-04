package io.gentalha.code.movie.di

import io.gentalha.code.movie.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin {}