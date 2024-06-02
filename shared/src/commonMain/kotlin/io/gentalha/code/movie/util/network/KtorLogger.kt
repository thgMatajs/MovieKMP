package io.gentalha.code.movie.util.network

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.request

private const val KTOR_LOG_TAG = "KtorLogger"
internal object KtorLogger {

    val CustomLoggerPlugin = createClientPlugin("CustomLoggerPlugin") {
        onRequest { request, content ->
            Napier.d(tag = KTOR_LOG_TAG) { "=============REQUEST==============" }
            Napier.d(tag = KTOR_LOG_TAG) { "${request.method.value} => ${request.url}" }
            Napier.d(tag = KTOR_LOG_TAG) { "BODY => ${request.body}" }
            Napier.d(tag = KTOR_LOG_TAG) { "=============END-REQUEST==============" }
        }

        onResponse {response ->
            Napier.d(tag = KTOR_LOG_TAG) { "=============RESPONSE==============" }
            Napier.d(tag = KTOR_LOG_TAG) { "${response.request.method.value} / ${response.status} => ${response.request.url}" }
            Napier.d(tag = KTOR_LOG_TAG) { "BODY => $response" }
            Napier.d(tag = KTOR_LOG_TAG) { "=============END-RESPONSE==============" }
        }
    }
}