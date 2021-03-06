package com.marysugar.hilt_http_compose_sample.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.marysugar.hilt_http_compose_sample.model.Poster
import com.marysugar.hilt_http_compose_sample.ui.custom.NetworkImage
import com.marysugar.hilt_http_compose_sample.ui.theme.HiltHttpComposeSampleTheme

@Composable
fun HomePosters(
  modifier: Modifier = Modifier,
  posters: List<Poster>,
  selectPoster: (Long) -> Unit,
) {
  Column(
    modifier = modifier
      .statusBarsPadding()
      .verticalScroll(rememberScrollState())
      .background(MaterialTheme.colors.background)
  ) {
    posters.forEach { poster ->
      Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(50.dp)
      ) {
        HomePoster(
          poster = poster,
          selectPoster = selectPoster
        )
      }
    }
  }
}

@Composable
private fun HomePoster(
  modifier: Modifier = Modifier,
  poster: Poster,
  selectPoster: (Long) -> Unit = {},
) {
  Surface(
    modifier = modifier
      .clickable(
        onClick = { selectPoster(poster.id) }
      ),
  ) {
    ConstraintLayout {
      val (image, title, content) = createRefs()
      NetworkImage(
        modifier = Modifier
          .aspectRatio(0.6f)
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
          },
        url = poster.poster,
      )

      Text(
        modifier = Modifier
          .constrainAs(title) {
            centerHorizontallyTo(parent)
            top.linkTo(image.bottom)
          }
          .padding(8.dp),
        text = poster.name,
        style = MaterialTheme.typography.subtitle1,
        textAlign = TextAlign.Center,
      )

      Text(
        modifier = Modifier
          .constrainAs(content) {
            centerHorizontallyTo(parent)
            top.linkTo(title.bottom)
          }
          .padding(horizontal = 8.dp)
          .padding(bottom = 12.dp),
        text = poster.playtime,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
      )
    }
  }
}

@Composable
@Preview(name = "HomePoster Light Theme")
private fun HomePosterPreviewLight() {
  HiltHttpComposeSampleTheme(darkTheme = false) {
    HomePoster(
      poster = Poster.mock()
    )
  }
}

@Composable
@Preview(name = "HomePoster Dark Theme")
private fun HomePosterPreviewDark() {
  HiltHttpComposeSampleTheme(darkTheme = true) {
    HomePoster(
      poster = Poster.mock()
    )
  }
}