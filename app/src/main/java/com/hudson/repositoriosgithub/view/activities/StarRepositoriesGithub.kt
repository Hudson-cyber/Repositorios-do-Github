package com.hudson.repositoriosgithub.view.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.driver.shopper.shopperentregador.core.network.CheckNetwork
import com.driver.shopper.shopperentregador.core.network.InternetTrafficChecker
import com.hudson.repositoriosgithub.R
import com.hudson.repositoriosgithub.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext

class StarRepositoriesGithub(override val coroutineContext: CoroutineContext) : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding

    private val internetTraffic by inject<InternetTrafficChecker>()
    private val checkNetwork by inject<CheckNetwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        statusInternet()
    }

    private fun statusInternet(){
        checkNetwork.observe(this) { status ->
            if (!status) {
                Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkTraffic(){
        launch {
            withContext(Dispatchers.IO) {
                val hasNetwork =
                    internetTraffic.internetIsConnected()
                if (!hasNetwork) {
                    withContext(Dispatchers.Main) {
                       // Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

}