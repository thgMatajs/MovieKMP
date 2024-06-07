package io.gentalha.code.movie

import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import platform.Foundation.NSBundle.Companion.mainBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.UIKit.UIDevice

class IOSPlatform : IPlatform {
    override val name: String = UIDevice.currentDevice.systemName()
    override val version: String get() = UIDevice.currentDevice.systemVersion
    override val appVersion: String
        get() = mainBundle.infoDictionary?.get("CFBundleVersion") as String
    override val language: String
        get() = NSLocale.currentLocale.languageCode
}

actual fun getPlatform(): IPlatform = IOSPlatform()

actual fun platformModule() = module {
    single { MovieService() }
    single { MovieRepository(get()) }
    singleOf<MovieListViewModel>(::MovieListViewModel)
}