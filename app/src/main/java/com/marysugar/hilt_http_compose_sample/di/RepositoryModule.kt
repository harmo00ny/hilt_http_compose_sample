package com.marysugar.hilt_http_compose_sample.di

import com.marysugar.hilt_http_compose_sample.network.DisneyService
import com.marysugar.hilt_http_compose_sample.persistence.PosterDao
import com.marysugar.hilt_http_compose_sample.ui.main.MainRepository
import com.marysugar.hilt_http_compose_sample.ui.main.MainRepository_Factory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        disneyService: DisneyService,
        posterDao: PosterDao
    ): MainRepository {
        return MainRepository(disneyService, posterDao)
    }
}