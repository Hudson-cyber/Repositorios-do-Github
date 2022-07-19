package com.hudson.repositoriosgithub.data.repositories

import com.hudson.repositoriosgithub.data.models.repositories_response.ItemsGithub
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

    private suspend fun dataUser(repositories: ItemsGithub): AppDataGithub? {
        val dataUserResponse = dataSource.getDataUser(repositories.dataRepository.owner.login)
        var repo : AppDataGithub? = null
        dataUserResponse.body().let { dataUser ->
            if (dataUserResponse.isSuccessful) {
                 repo = AppDataGithub(
                    nameRepository = repositories.dataRepository.name,
                    author = dataUser?.name,
                    numberOfStars = repositories.dataRepository.stargazersCount,
                    photoAuthor = dataUser?.avatarUrl
                )
            }
        }
        return repo

    }

}