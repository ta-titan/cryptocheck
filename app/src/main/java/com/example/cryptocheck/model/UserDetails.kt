package com.example.cryptocheck.model

import com.google.gson.annotations.SerializedName

data class UserDetails (
  @SerializedName("watchlist")val watchlist : List<Coin>
)