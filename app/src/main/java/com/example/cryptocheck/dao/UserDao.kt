package com.example.cryptocheck.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocheck.model.CurrentUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Query("SELECT * FROM current_user LIMIT 1")
  fun getCurrentUser() : Flow<CurrentUser>

  @Query("UPDATE current_user SET watchList = :watchList WHERE id = :id")
  fun addCoinToWatchList(watchList: List<String>, id : Int)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertCurrentUser(currentUser: CurrentUser)

  @Query("DELETE FROM current_user")
  fun deleteCurrentUser()

  @Query("UPDATE current_user SET userName = :userName WHERE id = :id")
  fun updateUserName(userName : String, id : Int)
}