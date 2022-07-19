package com.driver.shopper.shopperentregador.core.network

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import javax.net.SocketFactory
import kotlin.coroutines.suspendCoroutine

private const val TAG = "InternetTrafficChecker"

class InternetTrafficChecker {

    companion object {
        const val HOST_NAME = "8.8.8.8"
        const val PORT = 53
        const val TIMEOUT =1500

        fun execute(socketFactory: SocketFactory): Boolean {
            return try {
                Log.d(TAG, "PINGING google.")
                val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
                socket.connect(InetSocketAddress(HOST_NAME, PORT), TIMEOUT)
                socket.close()
                Log.d(TAG, "PING success.")
                true
            } catch (e: IOException) {
                Log.e(TAG, "Ping error: . $e")
                false
            }
        }
    }

    suspend fun internetIsConnected(): Boolean {
        return suspendCoroutine {
            try {
                val socket = Socket()
                val sockAddress = InetSocketAddress(HOST_NAME, PORT)
                socket.connect(sockAddress, TIMEOUT)// This will block no more than timeoutMs
                socket.close()
                Log.d(TAG, "PING success.")
                it.resumeWith(Result.success(true))
            } catch (e: Exception) {
                Log.e(TAG, "Ping error: . ${e.message}")
                it.resumeWith(Result.success(false))
            }
        }
    }
}