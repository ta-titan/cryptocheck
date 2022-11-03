package com.example.cryptocheck

import android.app.Application
import com.example.cryptocheck.repository.CoinRepo
import com.example.cryptocheck.repository.UserRepo
import com.example.cryptocheck.roomDB.CoinRoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CoinApplication : Application() {

  private val applicationScope = CoroutineScope(SupervisorJob())

  val database by lazy { CoinRoomDB.getDatabase(this, applicationScope) }
  val repository by lazy { CoinRepo(database.coinDao()) }
  val userRepo by lazy { UserRepo(database.userDao()) }
}