package com.marysugar.hilt_http_compose_sample.extensions

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/** sets a visibility of the disney views. */
@Stable
fun Modifier.visible(visibility: Boolean): Modifier {
  return if (visibility) {
    this.then(alpha(1f))
  } else {
    this.then(alpha(0f))
  }
}
