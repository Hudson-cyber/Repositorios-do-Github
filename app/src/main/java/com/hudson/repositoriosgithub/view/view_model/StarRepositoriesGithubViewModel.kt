package com.hudson.repositoriosgithub.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hudson.repositoriosgithub.data.repositories.StatusApi
import com.hudson.repositoriosgithub.domain.models.AppDataGithub
import com.hudson.repositoriosgithub.domain.repositories.DataAppRepository
import kotlinx.coroutines.launch

class StarRepositoriesGithubViewModel(private val dataAppRepository: DataAppRepository) : ViewModel() {
    private val repositoriesMutableLiveData: MutableLiveData<List<AppDataGithub>> = MutableLiveData()
    private val failureMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val failureLiveData: LiveData<String> = failureMutableLiveData
    val repositoriesLiveData: LiveData<List<AppDataGithub>> = repositoriesMutableLiveData

    fun updateRepositories(){
        viewModelScope.launch {
            when(val result = dataAppRepository.getAllRepositories()){
                is StatusApi.Successful -> repositoriesMutableLiveData.postValue(result.successful)
                is StatusApi.Failure ->failureMutableLiveData.postValue("Erro api")
                else -> 0
            }

        }
    }

}