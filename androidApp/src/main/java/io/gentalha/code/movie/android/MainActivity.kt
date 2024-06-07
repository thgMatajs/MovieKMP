package io.gentalha.code.movie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gentalha.code.movie.feature.movie_list.presentation.MovieListViewModel
import io.gentalha.code.movie.feature.movie_list.presentation.state.UIState
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

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

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        when (uiState) {
                            is UIState.Failure -> GreetingView(
                                "Error: ${(uiState as UIState.Failure).error.message ?: ""}"
                            )

                            UIState.Loading -> CircularProgressIndicator(Modifier.size(40.dp))
                            is UIState.Success<*> -> {
                                GreetingView("Filme: ${(uiState as UIState.Success<String>).result}")
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = {
                                    movieViewModel.getMovies(
                                        Random.nextInt(
                                            1,
                                            10
                                        )
                                    )
                                }) {
                                    Text(text = "New Title")
                                }
                            }
                        }
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
