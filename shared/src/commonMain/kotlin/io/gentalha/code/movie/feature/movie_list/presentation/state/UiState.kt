package io.gentalha.code.movie.feature.movie_list.presentation.state

sealed class UIState {
    data object Loading : UIState()
    data class Failure(val error: Throwable) : UIState()
    data class Success<T>(val result: T) : UIState()
}