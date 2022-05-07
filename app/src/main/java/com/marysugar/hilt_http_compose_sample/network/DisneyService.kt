package com.marysugar.hilt_http_compose_sample.network

import com.marysugar.hilt_http_compose_sample.model.Poster
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface DisneyService {
  @GET("DisneyPosters2.json")
  suspend fun fetchDisneyPosterList(): ApiResponse<List<Poster>>
}