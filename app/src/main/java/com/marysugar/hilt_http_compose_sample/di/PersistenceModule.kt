package com.marysugar.hilt_http_compose_sample.di

import android.app.Application
import androidx.room.Room
import com.marysugar.hilt_http_compose_sample.R
import com.marysugar.hilt_http_compose_sample.persistence.AppDatabase
import com.marysugar.hilt_http_compose_sample.persistence.AppDatabase_Impl
import com.marysugar.hilt_http_compose_sample.persistence.PosterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePosterDao(appDatabase: AppDatabase): PosterDao {
        return appDatabase.posterDao()
    }
}