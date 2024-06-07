package io.gentalha.code.movie.android

import android.app.Application
import io.gentalha.code.movie.android.core.di.appModule
import io.gentalha.code.movie.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(appModule)
        }
    }
}