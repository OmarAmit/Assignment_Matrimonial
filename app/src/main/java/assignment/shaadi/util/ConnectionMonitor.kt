package assignment.shaadi.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

class ConnectionMonitor constructor(private val application: Application) {
    internal fun startNetworkCallback() {
        val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(
                builder.build(),
                object : ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        Utility.isNetworkConnected = true
                    }

                    override fun onLost(network: Network) {
                        Utility.isNetworkConnected = false
                    }
                })
    }

    internal fun stopNetworkCallback() {
        val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }
}