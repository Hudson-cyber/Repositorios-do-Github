package com.hudson.repositoriosgithub.di

import com.driver.shopper.shopperentregador.core.network.CheckNetwork
import com.driver.shopper.shopperentregador.core.network.InternetTrafficChecker
import com.hudson.repositoriosgithub.data.data_sources.ApiGithubDataSource
import com.hudson.repositoriosgithub.data.service.GithubServices
import com.hudson.repositoriosgithub.domain.data_sources.GithubDataSource
import com.hudson.repositoriosgithub.view.Adapter.RepositoriesGithubAdapter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val locationModule = module {
    single<GithubServices> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GithubServices::class.java)
    }

    single { CheckNetwork(get()) }

    single { InternetTrafficChecker() }

    single { RepositoriesGithubAdapter(get()) }

    single<GithubDataSource>{ ApiGithubDataSource(get()) }

}