package io.gentalha.code.movie


import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {
    single { MovieService() }
    single { MovieRepository(get()) }
    viewModelOf<MovieListViewModel>(::MovieListViewModel)
}