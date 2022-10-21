package com.example.cryptocheck.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptocheck.dao.CoinDao
import com.example.cryptocheck.model.Coin

@Database(entities = arrayOf(Coin::class), version = 1, exportSchema = false)
public abstract class CoinRoomDB : RoomDatabase(){

  abstract fun coinDao() : CoinDao

  companion object {

    @Volatile
    private var INSTANCE : CoinRoomDB ? = null

    fun getDatabase(context: Context): CoinRoomDB {
      // if the INSTANCE is not null, then return it,
      // if it is, then create the database
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CoinRoomDB::class.java,
          "word_database"
        ).build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }
}