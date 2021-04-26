package com.ivanponomarev.di

import com.ivanponomarev.data.Repository
import com.ivanponomarev.domain.GetAllReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun providesUseCase(repository: Repository) : GetAllReposUseCase {
        return GetAllReposUseCase(repository)
    }
}