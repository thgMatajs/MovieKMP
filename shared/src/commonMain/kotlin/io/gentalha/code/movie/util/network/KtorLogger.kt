package io.gentalha.code.movie.util.network

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.request

private const val KTOR_LOG_TAG = "KtorLogger"

internal class KtorLogger {

    val customLoggerPlugin = createClientPlugin("CustomLoggerPlugin") {
        onRequest { request, content ->
            println("${KTOR_LOG_TAG}___=============REQUEST==============")
            println("${KTOR_LOG_TAG}___${request.method.value} => ${request.url}")
            println("${KTOR_LOG_TAG}___BODY => ${request.body}")
            println("${KTOR_LOG_TAG}___HEADERS => ${request.headers.entries()}")
            println("${KTOR_LOG_TAG}___=============END-REQUEST==============")
        }

        onResponse { response ->
            println("${KTOR_LOG_TAG}___=============RESPONSE==============")
            println("${KTOR_LOG_TAG}___${response.request.method.value} / ${response.status} => ${response.request.url}")
            println("${KTOR_LOG_TAG}___BODY => $response")
            println("${KTOR_LOG_TAG}___=============END-RESPONSE==============")
        }
    }
}