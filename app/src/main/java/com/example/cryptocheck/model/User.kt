package com.example.cryptocheck.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User (
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name="userName") val userName : String,
  @ColumnInfo(name="watchList") val watchList : List<String>,
)

