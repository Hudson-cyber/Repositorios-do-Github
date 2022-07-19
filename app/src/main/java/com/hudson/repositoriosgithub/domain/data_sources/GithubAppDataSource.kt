package com.hudson.repositoriosgithub.domain.data_sources

import com.hudson.repositoriosgithub.data.models.repositories_response.DataGithub
import com.hudson.repositoriosgithub.data.models.users_response.DataUsersGithub
import retrofit2.Response

interface GithubAppDataSource {
    suspend fun getDataRepository(): Response<DataGithub>
    suspend fun getDataUser(name: String): Response<DataUsersGithub>
}