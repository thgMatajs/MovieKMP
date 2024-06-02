package io.gentalha.code.movie.feature.movie_list.remote.service

import io.gentalha.code.movie.feature.movie_list.remote.model.MoviesResponse
import io.gentalha.code.movie.util.network.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://api.themoviedb.org/3/"

internal class MovieService : KtorApi() {
    override val baseUrl: String
        get() = BASE_URL

    suspend fun getMovies(page: Int): MoviesResponse = client.get {
        endPoint("movie/popular")
        parameter("language", "pt-BR")
        parameter("page", page)
    }.body()
}