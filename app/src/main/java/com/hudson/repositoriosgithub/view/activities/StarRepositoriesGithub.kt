package com.hudson.repositoriosgithub.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.driver.shopper.shopperentregador.core.network.CheckNetwork
import com.driver.shopper.shopperentregador.core.network.InternetTrafficChecker
import com.hudson.repositoriosgithub.R
import com.hudson.repositoriosgithub.databinding.ActivityMainBinding
import com.hudson.repositoriosgithub.view.Adapter.RepositoriesGithubAdapter
import com.hudson.repositoriosgithub.view.view_model.StarRepositoriesGithubViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarRepositoriesGithub : AppCompatActivity() {
    //falta
    //Paginação Scroll infinito
    //push to update

    private lateinit var binding: ActivityMainBinding
    val viewModelGit: StarRepositoriesGithubViewModel by viewModel()


    val internetTraffic by inject<InternetTrafficChecker>()
    //private val checkNetwork by inject<CheckNetwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //statusInternet()
        viewModelGit.updateRepositories()

        viewModelGit.repositoriesLiveData.observe(this){
            val adapter = RepositoriesGithubAdapter(it)
            binding.recyclerRepository.adapter = adapter
        }
    }

    /*private fun statusInternet() {
        checkNetwork.observe(this) { status ->
            if (!status) {
                Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
}