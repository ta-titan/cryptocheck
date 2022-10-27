package com.example.cryptocheck.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  // queries for user table
  @Query("SELECT * FROM user_table")
  fun getAllUsersFromRoomDb() : Flow<List<User>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertUser(user: User)

  @Query("DELETE FROM user_table")
  fun deleteAllUsers()


  // Queries for current user table
  @Query("SELECT * FROM current_user LIMIT 1")
  fun getCurrentUser() : Flow<CurrentUser>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertCurrentUser(currentUser: CurrentUser)

  @Query("DELETE FROM current_user")
  fun deleteCurrentUser()
}