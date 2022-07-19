package com.hudson.repositoriosgithub.di

import androidx.lifecycle.viewmodel.viewModelFactory
import com.driver.shopper.shopperentregador.core.network.CheckNetwork
import com.driver.shopper.shopperentregador.core.network.InternetTrafficChecker
import com.hudson.repositoriosgithub.data.data_sources.GithubApiDataSource
import com.hudson.repositoriosgithub.data.repositories.DataApiRepository
import com.hudson.repositoriosgithub.data.service.GithubServices
import com.hudson.repositoriosgithub.domain.data_sources.GithubAppDataSource
import com.hudson.repositoriosgithub.domain.repositories.DataAppRepository
import com.hudson.repositoriosgithub.view.Adapter.RepositoriesGithubAdapter
import com.hudson.repositoriosgithub.view.view_model.StarRepositoriesGithubViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
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

    viewModel{
        StarRepositoriesGithubViewModel(get())
    }
    single { CheckNetwork(get()) }

    single { InternetTrafficChecker() }

    single { RepositoriesGithubAdapter(get()) }

    single<GithubAppDataSource>{ GithubApiDataSource(get()) }

    single<DataAppRepository>{ DataApiRepository(get()) }

}