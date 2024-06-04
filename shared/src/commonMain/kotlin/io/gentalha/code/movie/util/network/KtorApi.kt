package io.gentalha.code.movie.util.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

abstract class KtorApi {

    protected abstract val baseUrl: String

    protected val client = HttpClient {

        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(HttpRequestRetry) {
            maxRetries = 3
            retryIf { _, httpResponse -> !httpResponse.status.isSuccess() }
        }

        install(KtorLogger().customLoggerPlugin)
//        install(Logging) {
//            logger = KtorLogger()
//            level = LogLevel.ALL
//        }
    }

    protected fun HttpRequestBuilder.endPoint(path: String) {
        bearerAuth(API_KEY)
        accept(ContentType.Application.Json)
        url("$baseUrl$path")
    }
}