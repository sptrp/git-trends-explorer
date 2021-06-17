package com.ivanponomarev.data

import com.ivanponomarev.domain.Repo
import dagger.Reusable
import retrofit2.http.GET

@Reusable
interface ReposDataSource {
    @GET("/")
    suspend fun getAllRepos() : List<Repo>
}