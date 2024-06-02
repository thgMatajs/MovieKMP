package io.gentalha.code.movie.feature.movie_list.remote.service

import io.gentalha.code.movie.feature.movie_list.remote.model.MoviesResponse
import io.gentalha.code.movie.util.network.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService : KtorApi() {
    override val baseUrl: String
        get() = TODO("Not yet implemented")

    suspend fun getMovies(page: Int): MoviesResponse = client.get {
        endPoint("movie/popular")
        parameter("language", "pt-BR")
        parameter("page", page)
    }.body()
}