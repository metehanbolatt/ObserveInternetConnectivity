package com.metehanbolat.observeinternetconnectivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.metehanbolat.observeinternetconnectivity.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModelFactory = NetworkViewModelFactory()
    private val viewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launchWhenCreated {
            viewModel.isConnected.collectLatest {
                binding.apply {
                    if (it) connected() else notConnected()
                }
            }
        }
    }

    private fun connected() {
        binding.apply {
            wifiImage.setImageResource(R.drawable.ic_wifi)
            wifiText.text = resources.getString(R.string.connected)
        }
    }

    private fun notConnected() {
        binding.apply {
            wifiImage.setImageResource(R.drawable.ic_wifi_off)
            wifiText.text = resources.getString(R.string.not_connected)
        }
    }
}