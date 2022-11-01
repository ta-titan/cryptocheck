package com.example.cryptocheck.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cryptocheck.dao.UserDao
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.User
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class UserRepo(private val userDao: UserDao) {

  val allUsers: Flow<List<User>> = userDao.getAllUsersFromRoomDb()

  val currentUser: Flow<CurrentUser> = userDao.getCurrentUser()

  fun addCoinToWatchList(coin: Coin, add : Boolean) {
    CoroutineScope(Dispatchers.IO).launch {

      var watchList = currentUser.first().watchList.toMutableList()
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