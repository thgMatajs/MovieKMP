package io.gentalha.code.movie.feature.movie_list.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val isFavorite: Boolean = false
) {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun getBackdropUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
}