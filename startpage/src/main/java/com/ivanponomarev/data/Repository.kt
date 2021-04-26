package com.ivanponomarev.data

import dagger.Reusable
import dagger.hilt.InstallIn
import javax.inject.Inject

@Reusable
class Repository @Inject constructor (private val reposDataSource: ReposDataSource) {

    suspend fun getAllRepos() = reposDataSource.getAllRepos()
}