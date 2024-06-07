package io.gentalha.code.movie.feature.movie_list.remote.service

import io.gentalha.code.movie.feature.movie_list.remote.model.MoviesResponse
import io.gentalha.code.movie.core.network.KtorApi
import io.gentalha.code.movie.core.network.endPoint
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.themoviedb.org/3/"

internal class MovieService(private val ktor: KtorApi) {

    suspend fun getMovies(page: Int): MoviesResponse = ktor.client.get {
        endPoint(BASE_URL,"movie/popular")
        parameter("language", "pt-BR")
        parameter("page", page)
    }.body()
}