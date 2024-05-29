package io.gentalha.code.movie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform