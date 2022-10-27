package com.example.cryptocheck.viewmodel

import androidx.lifecycle.*
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.repository.UserRepo

class UserViewModel(private val repo: UserRepo) : ViewModel() {

  val currentUser : LiveData<CurrentUser> = repo.currentUser

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