package com.example.cryptocheck.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.cryptocheck.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

  @Query("SELECT * FROM coin_table")
  fun getAllCoinsFromRoomDB() : Flow<List<Coin>>

}