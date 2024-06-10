package io.gentalha.code.movie.feature.movie_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.gentalha.code.movie.feature.movie_list.data.MovieRepository
import io.gentalha.code.movie.feature.movie_list.presentation.state.Resulted
import io.gentalha.code.movie.feature.movie_list.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class MovieListViewModel : ViewModel(), KoinComponent {

    /**Esta sendo injetado desta forma, pois a classe MovieRepository é interna
    e não pode ser injetada via construtor, pois a classe MovieListViewModel
    sera usada em outros modulos.
     **/
    private val movieRepository: MovieRepository by inject()

    private val _movieUiState = MutableStateFlow<UIState>(UIState.Loading)
    val movieUiState = _movieUiState.asStateFlow()

    private val _movie = MutableStateFlow("")
    val movie: StateFlow<String> = _movie.asStateFlow()

    private val _movieResult = MutableStateFlow<Resulted>(Resulted.Loading)
    val movieResult: StateFlow<Resulted> = _movieResult.asStateFlow()

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        _movieUiState.update { UIState.Loading }
        viewModelScope.launch {
            _movie.update { "" }
            delay(1000L)
            movieRepository.getMovies(page)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _movieUiState.update { UIState.Loading }
                    _movieResult.update { Resulted.Loading }
                    _movie.update { "" }
                }
                .catch { error ->
                    _movieUiState.update { UIState.Failure(error) }
                    _movie.update { error.message ?: "null error" }
                    _movieResult.update { Resulted.Error(error.message ?: "null error") }
                }
                .collect { movies ->
                    val title = movies.random().title
                    _movieUiState.update {
                        UIState.Success(title)
                    }
                    _movie.update { title }
                    _movieResult.update { Resulted.Success(title) }

                }
        }
    }
}