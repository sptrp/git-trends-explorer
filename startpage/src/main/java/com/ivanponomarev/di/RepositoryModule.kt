package com.ivanponomarev.di

import com.ivanponomarev.data.ReposDataSource
import com.ivanponomarev.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideRepository(reposDataSource: ReposDataSource) : Repository {
        return Repository(reposDataSource)
    }

}