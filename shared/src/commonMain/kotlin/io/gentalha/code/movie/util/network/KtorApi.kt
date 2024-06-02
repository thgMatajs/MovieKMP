package io.gentalha.code.movie.util.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal abstract class KtorApi {

    protected abstract val baseUrl: String

    protected val client = HttpClient {
        install(KtorLogger.CustomLoggerPlugin)

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

        install(Auth) {
            bearer {
                BearerTokens(API_KEY, "")
            }
        }
    }

    protected fun HttpRequestBuilder.endPoint(path: String) {
        url {
            takeFrom(baseUrl)
            path(path)
            contentType(ContentType.Application.Json)
        }
    }

}