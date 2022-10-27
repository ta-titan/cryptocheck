package com.example.cryptocheck.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_user")
class CurrentUser(
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name="watchList") val watchList : List<Int>,
  @ColumnInfo(name = "userName") val userName: String
)