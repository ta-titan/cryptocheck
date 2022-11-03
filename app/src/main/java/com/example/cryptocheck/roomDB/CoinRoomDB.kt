package com.example.cryptocheck.roomDB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cryptocheck.dao.CoinDao
import com.example.cryptocheck.dao.UserDao
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.User
import com.example.cryptocheck.util.ListToStringConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Coin::class, User::class, CurrentUser::class], version = 2, exportSchema = false)
@TypeConverters(ListToStringConverter::class)
abstract class CoinRoomDB : RoomDatabase(){

  abstract fun coinDao() : CoinDao
  abstract fun userDao() : UserDao

  private class CoinDBCallback(
    private val scope: CoroutineScope
  ) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
      super.onCreate(db)
      Log.d("coinDBcallback", "onCreate")
      INSTANCE?.let {
        scope.launch {

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
        Log.d("coinRoomDB", "databaseBuilder")
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CoinRoomDB::class.java,
          "coin_database"
        ).addCallback(CoinDBCallback(scope))
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }
}