package com.hudson.repositoriosgithub.data.repositories

import com.hudson.repositoriosgithub.data.models.repositories_response.DataRepositoryGithub
import com.hudson.repositoriosgithub.domain.data_sources.GithubAppDataSource
import com.hudson.repositoriosgithub.domain.models.AppDataGithub
import com.hudson.repositoriosgithub.domain.repositories.DataAppRepository

class DataApiRepository(private var dataSource: GithubAppDataSource) : DataAppRepository {

    override suspend fun getAllRepositories(): StatusApi<String,List<AppDataGithub>> {
        val repositoriesResponse = dataSource.getDataRepository()
        val listAppGithub: MutableList<AppDataGithub> = mutableListOf()
        return if (repositoriesResponse.isSuccessful) {
            repositoriesResponse.body().let { repositoriesGit ->
                for (repositories in repositoriesGit!!.items) {
                    val data = dataUser(repositories)
                    if(data != null)
                        listAppGithub.add(data)
                }
            }
            StatusApi.Successful(listAppGithub)
        }else{
            StatusApi.Failure("erro")
        }
    }

    private suspend fun dataUser(repositories: DataRepositoryGithub): AppDataGithub? {
        val dataUserResponse = dataSource.getDataUser(repositories.owner.login)
        var repo : AppDataGithub? = null
        dataUserResponse.body().let { dataUser ->
            if (dataUserResponse.isSuccessful) {
                 repo = AppDataGithub(
                    nameRepository = repositories.name,
                    author = dataUser?.name,
                    numberOfStars = repositories.stargazersCount,
                    photoAuthor = dataUser?.avatarUrl
                )
            }
        }
        return repo

    }

}