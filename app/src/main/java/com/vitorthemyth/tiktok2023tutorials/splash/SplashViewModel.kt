package com.vitorthemyth.tiktok2023tutorials.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        splashDelay()
    }

   private fun splashDelay() = viewModelScope.launch {
       delay(DELAY)
       _isLoading.value = false
   }

    companion object{
        private const val DELAY = 3000L
    }
}