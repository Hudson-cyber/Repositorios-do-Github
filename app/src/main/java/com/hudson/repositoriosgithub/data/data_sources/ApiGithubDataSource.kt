package com.hudson.repositoriosgithub.data.data_sources

import com.hudson.repositoriosgithub.data.service.GithubServices
import com.hudson.repositoriosgithub.domain.data_sources.GithubDataSource
import com.hudson.repositoriosgithub.domain.models.AppDataGithub

class ApiGithubDataSource(private val api: GithubServices) : GithubDataSource {
    override suspend fun getDataRepository(): List<AppDataGithub> {
        val repositoriesResponse = api.getRepositories()
        val listAppGithub: MutableList<AppDataGithub> = mutableListOf()
        if (repositoriesResponse.isSuccessful) {
            repositoriesResponse.body().let{
                for (repositories in it!!.items) {
                        val repo = AppDataGithub(
                            nameRepository = repositories.name,
                            author = "",
                            numberOfStars = repositories.stargazersCount,
                            photoAuthor = ""
                        )
                    listAppGithub.add(repo)
                }
            }

        }
        return listAppGithub
    }

}