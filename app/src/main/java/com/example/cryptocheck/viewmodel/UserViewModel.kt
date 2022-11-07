package com.example.cryptocheck.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.repository.UserRepo

class UserViewModel(private val repo: UserRepo): ViewModel() {

  val currentUserName  = ObservableField<String>()
  val currentUser : LiveData<CurrentUser> = repo.currentUser.asLiveData()

  fun updateWatchList(coin: Coin, add: Boolean) {
    repo.updateWatchList(coin, add)
  }

  fun updateUsername(name: String) {
    repo.updateUsername(name)
  }
}

class UserViewModelFactory(private val repo: UserRepo) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass : Class<T>) : T {
    if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return UserViewModel(repo) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}