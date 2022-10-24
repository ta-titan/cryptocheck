package com.example.cryptocheck.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocheck.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

  @Query("SELECT * FROM coin_table")
  fun getAllCoinsFromRoomDB() : Flow<List<Coin>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(coin: Coin)

  @Query("DELETE FROM coin_table")
  fun deleteAll()
}