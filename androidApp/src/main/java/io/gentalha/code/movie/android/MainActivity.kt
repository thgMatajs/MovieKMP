package io.gentalha.code.movie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.presentation.state.UIState
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val movieViewModel = koinViewModel<MovieListViewModel>()
                val uiState: UIState by movieViewModel.movieUiState.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    when (uiState) {
                        is UIState.Failure -> GreetingView((uiState as UIState.Failure).error.message ?: "")
                        UIState.Loading -> CircularProgressIndicator()
                        is UIState.Success<*> -> GreetingView((uiState as UIState.Success<*>).result.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
