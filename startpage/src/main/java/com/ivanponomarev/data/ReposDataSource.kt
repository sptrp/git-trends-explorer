package com.ivanponomarev.data

import com.ivanponomarev.domain.Repo
import retrofit2.http.GET


interface ReposDataSource {
    @GET("/")
    suspend fun getAllRepos() : List<Repo>
}