package com.hudson.repositoriosgithub.domain.repositories

import com.hudson.repositoriosgithub.data.repositories.StatusApi
import com.hudson.repositoriosgithub.domain.models.AppDataGithub

interface DataAppRepository {
    suspend fun getAllRepositories(): StatusApi<String, List<AppDataGithub>>
}