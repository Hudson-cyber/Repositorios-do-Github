package com.driver.shopper.shopperentregador.core.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData


class CheckNetwork(private val context: Context) : LiveData<Boolean>() {

    private val TAG = "Conection"
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()

    private fun checkValidaNetworks() {
        postValue(validNetworks.size > 0)
        //verifica se tem 1 ou mais redes ativas connectadas
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActive() {
        super.onActive()
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun createNetworkCallback() = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG, "onAvaliable : $network")
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val isInternet = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            Log.d(TAG, "onAvaliable : $network,$isInternet")
            if (isInternet == true) {
                val hasInternet = InternetTrafficChecker.execute(network.socketFactory)
                if (hasInternet) {
                    Log.d(TAG, "onAvailable: adding network. $network")
                    validNetworks.add(network)
                    checkValidaNetworks()
                }
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG, "onLost $network")
            validNetworks.remove(network)
            checkValidaNetworks()
        }
    }


}