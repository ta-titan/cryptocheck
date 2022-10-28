package com.example.cryptocheck.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.repository.UserRepo

class UserViewModel(private val repo: UserRepo) : ViewModel() {

  val currentUserName  = ObservableField<String>()
  val currentUserWatchList = ObservableField<List<Int>>()

  fun syncUserViewModel() {
    repo.currentUser.subscribe{
      currentUserName.set(it.userName)
      currentUserWatchList.set(it.watchList)
    }
  }


  fun addCoinToWatchList(coin : Coin, add : Boolean ) {
    repo.addCoinToWatchList(coin, add)
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