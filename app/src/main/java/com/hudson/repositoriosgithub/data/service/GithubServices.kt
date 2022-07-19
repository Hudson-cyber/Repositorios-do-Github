package com.hudson.repositoriosgithub.data.service

import com.hudson.repositoriosgithub.data.models.repositories_response.DataGithub
import com.hudson.repositoriosgithub.data.models.users_response.DataUsersGithub
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubServices {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositories(): Response<DataGithub>

    @GET("users/")
    suspend fun getUser(@Path("name") name: String): Response<DataUsersGithub>
}
