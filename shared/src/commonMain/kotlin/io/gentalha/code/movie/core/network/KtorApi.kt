package io.gentalha.code.movie.core.network

import io.gentalha.code.movie.Platform
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorApi {
    val client = HttpClient {

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

        defaultRequest {
            defaultHeaders()
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

    private fun DefaultRequest.DefaultRequestBuilder.defaultHeaders() {
        val platform = Platform()
        header(HeaderName.Channel, platform.name)
        header(HeaderName.AppId, platform.version)
        header(HeaderName.Version, platform.appVersion)
        header(HeaderName.AcceptLanguage, platform.language)
        bearerAuth(API_KEY)
        accept(ContentType.Application.Json)
    }
}


fun HttpRequestBuilder.endPoint(baseUrl: String, path: String) {
    url("$baseUrl$path")
}


internal object HeaderName {
    val Channel: String = "Channel"
    val AppId: String = "AppId"
    val Version: String = "Version"
    val AcceptLanguage: String = "Accept-Language"
}