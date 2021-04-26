package com.ivanponomarev.domain

import com.ivanponomarev.data.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class GetAllReposUseCase @Inject constructor (private val repository: Repository) {

    suspend fun getAllRepos() = repository.getAllRepos()
}