package io.gentalha.code.movie.util.network

import io.ktor.client.plugins.logging.Logger

private const val KTOR_LOG_TAG = "KtorClient"
internal class KtorLogger : Logger {
    override fun log(message: String) {
        println("$KTOR_LOG_TAG --> $message")
    }
}