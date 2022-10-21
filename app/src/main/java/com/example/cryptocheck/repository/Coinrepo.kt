package com.example.cryptocheck.repository

import androidx.annotation.WorkerThread
import com.example.cryptocheck.dao.CoinDao
import com.example.cryptocheck.model.Coin
import kotlinx.coroutines.flow.Flow

class Coinrepo (private val coinDao: CoinDao) {

  val allCoins: Flow<List<Coin>> = coinDao.getAllCoinsFromRoomDB()

}