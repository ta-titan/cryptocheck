package com.example.cryptocheck

import android.app.Application
import com.example.cryptocheck.repository.Coinrepo
import com.example.cryptocheck.roomDB.CoinRoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CoinApplication : Application() {

  val applicationScope = CoroutineScope(SupervisorJob())

  val database by lazy { CoinRoomDB.getDatabase(this) }
  val repository by lazy { Coinrepo(database.coinDao()) }
}