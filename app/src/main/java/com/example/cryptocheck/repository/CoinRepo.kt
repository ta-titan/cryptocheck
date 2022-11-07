package com.example.cryptocheck.repository

import com.example.cryptocheck.dao.CoinDao
import com.example.cryptocheck.model.Coin
import kotlinx.coroutines.flow.Flow

class CoinRepo (coinDao: CoinDao) {
  val allCoins: Flow<List<Coin>> = coinDao.getAllCoinsFromRoomDB()
}