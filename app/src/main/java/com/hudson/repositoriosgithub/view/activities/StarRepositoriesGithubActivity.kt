package com.hudson.repositoriosgithub.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hudson.repositoriosgithub.R
import com.hudson.repositoriosgithub.databinding.ActivityMainBinding
import com.hudson.repositoriosgithub.view.Adapter.RepositoriesGithubAdapter
import com.hudson.repositoriosgithub.view.view_model.StarRepositoriesGithubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class StarRepositoriesGithubActivity : AppCompatActivity() {
    //falta
    //Paginação Scroll infinito
    //push to update

    private lateinit var binding: ActivityMainBinding
    private val viewModelGit: StarRepositoriesGithubViewModel by viewModel()
   // val internetTraffic by inject<InternetTrafficChecker>()
    //private val checkNetwork by inject<CheckNetwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //statusInternet()
        viewModelGit.updateRepositories()

        viewModelGit.repositoriesLiveData.observe(this){
            val adapter = RepositoriesGithubAdapter(it)
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerRepository.layoutManager = layoutManager
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