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

@Database(entities = [Coin::class, User::class, CurrentUser::class], version = 2, exportSchema = false)
@TypeConverters(ListToStringConverter::class)
abstract class CoinRoomDB: RoomDatabase(){

  abstract fun coinDao(): CoinDao
  abstract fun userDao(): UserDao

  private class CoinDBCallback: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
      super.onCreate(db)
      Log.d("coinDBcallback", "onCreate")
    }
  }

  companion object {
    @Volatile
    private var INSTANCE: CoinRoomDB? = null

    fun getDatabase(context: Context): CoinRoomDB {
      Log.d("coinRoomDB", "getDatabase")
      return INSTANCE ?: synchronized(this) {
        Log.d("coinRoomDB", "databaseBuilder")
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CoinRoomDB::class.java,
          "coin_database"
        ).addCallback(CoinDBCallback())
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}