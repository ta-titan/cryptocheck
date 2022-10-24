package com.example.cryptocheck.roomDB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cryptocheck.dao.CoinDao
import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.model.Coin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Coin::class), version = 1, exportSchema = false)
public abstract class CoinRoomDB : RoomDatabase(){

  abstract fun coinDao() : CoinDao


  private class CoinDBCallback(
    private val scope: CoroutineScope
  ) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
      super.onCreate(db)
      Log.d("coinDBcallback", "onCreate")
      INSTANCE?.let { database ->
        scope.launch {
          var coinDao = database.coinDao()

          coinDao.deleteAll()

          val coins : List<Coin> = Datasource().loadCoins()

          for ( coin in coins ) {
            coinDao.insert(coin)
          }
        }
      }
    }
  }

  companion object {

    @Volatile
    private var INSTANCE : CoinRoomDB ? = null

    fun getDatabase(
      context: Context,
      scope: CoroutineScope
    ): CoinRoomDB {
      // if the INSTANCE is not null, then return it,
      // if it is, then create the database
      Log.d("coinRoomDB", "getDatabase")
      return INSTANCE ?: synchronized(this) {
        Log.d("coinRoomDB", "databseBuilder")
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CoinRoomDB::class.java,
          "coin_database"
        ).addCallback(CoinDBCallback(scope))
          .build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }
}