package io.gentalha.code.movie

class Platform {
    private val platform: IPlatform = getPlatform()

    val name: String get() = platform.name

    val version: String get() = platform.version

    val appVersion: String get() = platform.appVersion


    val language: String get() = platform.language

}