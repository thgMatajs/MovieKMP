package io.gentalha.code.movie.android.core.di

import io.gentalha.code.movie.APP_VERSION_NAMED
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(APP_VERSION_NAMED)) {
        androidContext().packageManager
            .getPackageInfo(androidContext().packageName, 0)
            .versionName
    }
}