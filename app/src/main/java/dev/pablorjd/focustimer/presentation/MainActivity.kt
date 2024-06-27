package dev.pablorjd.focustimer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.pablorjd.focustimer.presentation.home.HomeScreen
import dev.pablorjd.focustimer.presentation.home.HomeScreenViewModel
import dev.pablorjd.focustimer.presentation.theme.FocusTimerTheme

//internal val viewModel:HomeScreenViewModel = HomeScreenViewModel()
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            FocusTimerTheme(windowSize = windowSize.widthSizeClass) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
                    HomeScreen()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FocusTimerTheme {
        HomeScreen()
    }
}