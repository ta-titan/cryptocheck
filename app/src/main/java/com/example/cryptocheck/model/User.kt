package com.example.cryptocheck.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name="username") val username : String,
  @ColumnInfo(name="userDetails") val userDetails : UserDetails,
)

