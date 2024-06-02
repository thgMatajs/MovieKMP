package io.gentalha.code.movie.feature.movie_list.data

import io.gentalha.code.movie.feature.movie_list.remote.service.MovieService
import kotlinx.coroutines.flow.flow

internal class MovieRepository(private val movieService: MovieService) {

    fun getMovies() = flow {
        emit(movieService.getMovies(1))
    }
}