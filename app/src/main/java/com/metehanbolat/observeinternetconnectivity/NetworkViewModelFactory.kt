package com.metehanbolat.observeinternetconnectivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NetworkViewModelFactory(
    private val listenNetwork: ListenNetwork? = null
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ListenNetwork::class.java).newInstance(listenNetwork)
    }
}