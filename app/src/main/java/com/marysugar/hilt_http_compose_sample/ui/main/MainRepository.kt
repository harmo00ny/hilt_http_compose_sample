package com.marysugar.hilt_http_compose_sample.ui.main

import androidx.annotation.WorkerThread
import com.marysugar.hilt_http_compose_sample.model.Poster
import com.marysugar.hilt_http_compose_sample.network.DisneyService
import com.marysugar.hilt_http_compose_sample.persistence.PosterDao
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val disneyService: DisneyService,
  private val posterDao: PosterDao
) {
  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadDisneyPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Poster> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      disneyService.fetchDisneyPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request is fails.
        // e.g. internal server error.
        .onFailure { onError(this) }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}