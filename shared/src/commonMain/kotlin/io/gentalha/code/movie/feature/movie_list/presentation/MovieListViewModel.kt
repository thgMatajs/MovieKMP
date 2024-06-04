package io.gentalha.code.movie.feature.movie_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

import org.koin.core.component.inject

open class MovieListViewModel : ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    private val _movieUiState = MutableStateFlow<UIState>(UIState.Loading)
    val movieUiState = _movieUiState.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movieRepository.getMovies()
                .flowOn(Dispatchers.IO)
                .onStart {
                    _movieUiState.update { UIState.Loading }
                }
                .catch { error ->
                    _movieUiState.update { UIState.Failure(error) }
                }
                .collect { movies ->
                    _movieUiState.update {
                        UIState.Success(movies.first().title)
                    }

                }
        }
    }
}