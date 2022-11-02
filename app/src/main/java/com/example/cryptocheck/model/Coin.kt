package com.example.cryptocheck.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "coin_table")
class Coin(
  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "id") val id: String,
  @ColumnInfo(name = "symbol") val symbol: String,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "price") val price: Double?,
  @ColumnInfo(name = "volume") val volume: Double?,
  @ColumnInfo(name = "percentChange1H") val percentChange1H: Double?,
  @ColumnInfo(name = "percentChange1D") val percentChange1D: Double?,
  @ColumnInfo(name = "percentChange1W") val percentChange1W: Double?
)