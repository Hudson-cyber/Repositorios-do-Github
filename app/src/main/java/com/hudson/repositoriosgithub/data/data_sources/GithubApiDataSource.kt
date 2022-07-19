package com.hudson.repositoriosgithub.data.data_sources

import com.hudson.repositoriosgithub.data.models.repositories_response.DataGithub
import com.hudson.repositoriosgithub.data.models.users_response.DataUsersGithub
import com.hudson.repositoriosgithub.data.service.GithubServices
import com.hudson.repositoriosgithub.domain.data_sources.GithubAppDataSource
import retrofit2.Response

class GithubApiDataSource(private val api: GithubServices) : GithubAppDataSource {
    override suspend fun getDataRepository(): Response<DataGithub> {
        return api.getRepositories()
    }

    override suspend fun getDataUser(name: String): Response<DataUsersGithub> {
        return api.getUser(name)
    }

}