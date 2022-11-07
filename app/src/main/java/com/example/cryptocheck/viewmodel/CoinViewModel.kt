package com.example.cryptocheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.repository.CoinRepo

class CoinViewModel(repository : CoinRepo): ViewModel() {
  val allCoins : LiveData<List<Coin>> = repository.allCoins.asLiveData()
}

class CoinViewModelFactory(private val repository: CoinRepo): ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return CoinViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}