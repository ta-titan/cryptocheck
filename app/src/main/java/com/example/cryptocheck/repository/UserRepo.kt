package com.example.cryptocheck.repository

import android.util.Log
import com.example.cryptocheck.dao.UserDao
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class UserRepo(private val userDao: UserDao) {

  val currentUser: Flow<CurrentUser> = userDao.getCurrentUser()

  fun addCoinToWatchList(coin: Coin, add : Boolean) {
    CoroutineScope(Dispatchers.IO).launch {

      val watchList = currentUser.first().watchList.toMutableList()
      if ( add ) {
        watchList.add(coin.id)
      }
      else {
        watchList.remove(coin.id)
      }
      Log.d("Watchlist upd in repo", watchList.size.toString())
      userDao.addCoinToWatchList(watchList.toImmutableList(), currentUser.first().id)
    }
  }

  fun updateUsername(name : String) {
    CoroutineScope(Dispatchers.IO).launch {
      val id = currentUser.first().id
      userDao.updateUserName(name, id)
    }
  }

}