package io.gentalha.code.movie


import android.os.Build.VERSION.SDK_INT
import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.Locale

const val APP_VERSION_NAMED = "APP_VERSION"
class AndroidPlatform : IPlatform, KoinComponent {

    private val appVersionName: String by inject(named(APP_VERSION_NAMED))
    override val name: String = "Android"

    override val version: String
        get() = SDK_INT.toString()
    override val appVersion: String
        get() = appVersionName // vou tentar pegar via DI, ou usar a lib: https://github.com/yshrsmz/BuildKonfig

    override val language: String
        get() = Locale.getDefault().language
}

actual fun getPlatform(): IPlatform = AndroidPlatform()

actual fun platformModule() = module {
    single { MovieService() }
    single { MovieRepository(get()) }
    viewModelOf<MovieListViewModel>(::MovieListViewModel)
}