package com.example.cryptocheck.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_table")
class Coin (
  @PrimaryKey(autoGenerate = true) val id: Int,
  @ColumnInfo(name = "symbol")  val symbol: String,
  @ColumnInfo(name = "name") val name : String,
  @ColumnInfo(name = "price") val price : Int,
  @ColumnInfo(name = "volume") val volume : Int,
  @ColumnInfo(name = "percentChange1H") val percentChange1H : Int,
  @ColumnInfo(name = "percentChange1D") val percentChange1D : Int,
  @ColumnInfo(name = "percentChange1W") val percentChange1W : Int
)