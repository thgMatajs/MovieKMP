package io.gentalha.code.movie

import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    single { MovieService() }
    single { MovieRepository(get()) }
    singleOf<MovieListViewModel>(::MovieListViewModel)
}