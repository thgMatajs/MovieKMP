package io.gentalha.code.movie.feature.movie_list.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponse(
    @SerialName("results")
    val movies: List<MovieResponse>
)