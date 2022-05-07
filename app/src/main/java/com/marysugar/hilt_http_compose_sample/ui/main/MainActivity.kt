package com.marysugar.hilt_http_compose_sample.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.marysugar.hilt_http_compose_sample.ui.root.RootViewModel
import com.marysugar.hilt_http_compose_sample.ui.theme.HiltHttpComposeSampleTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @VisibleForTesting
  internal val viewModel: RootViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
        HiltHttpComposeSampleTheme {
          MainScreen()
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  HiltHttpComposeSampleTheme {
    Greeting("Android")
  }
}