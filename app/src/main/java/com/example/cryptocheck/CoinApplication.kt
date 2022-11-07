package com.example.cryptocheck

import android.app.Application
import com.example.cryptocheck.repository.CoinRepo
import com.example.cryptocheck.repository.UserRepo
import com.example.cryptocheck.roomDB.CoinRoomDB

class CoinApplication : Application() {

  val database by lazy { CoinRoomDB.getDatabase(this) }
  val repository by lazy { CoinRepo(database.coinDao()) }
  val userRepo by lazy { UserRepo(database.userDao()) }
}