package com.ivanponomarev.data

import dagger.hilt.InstallIn
import javax.inject.Inject

class Repository @Inject constructor (private val reposDataSource: ReposDataSource) {

    suspend fun getAllRepos() = reposDataSource.getAllRepos()
}