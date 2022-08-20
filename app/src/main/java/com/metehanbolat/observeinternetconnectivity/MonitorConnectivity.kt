package com.metehanbolat.observeinternetconnectivity

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object MonitorConnectivity {

    fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return ActiveNetwork.isConnected(connectivityManager)
    }
}

interface ConnectedCompat {
    fun isConnected(connectivityManager: ConnectivityManager): Boolean
}

object ActiveNetwork: ConnectedCompat {
    override fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) == true
        } else {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}