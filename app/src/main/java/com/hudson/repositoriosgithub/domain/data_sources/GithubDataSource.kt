package com.hudson.repositoriosgithub.domain.data_sources

import com.hudson.repositoriosgithub.domain.models.AppDataGithub

interface GithubDataSource {
 suspend fun getDataRepository(): List<AppDataGithub>
}