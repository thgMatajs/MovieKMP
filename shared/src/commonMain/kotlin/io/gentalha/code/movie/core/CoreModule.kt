package io.gentalha.code.movie.core

import io.gentalha.code.movie.core.network.KtorApi
import org.koin.dsl.module

val coreModule = module {
    single { KtorApi() }

}