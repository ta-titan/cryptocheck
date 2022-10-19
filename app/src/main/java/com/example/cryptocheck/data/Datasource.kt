package com.example.cryptocheck.data

import com.example.cryptocheck.R
import com.example.cryptocheck.model.Coin

class Datasource {

  fun loadCoins(): List<Coin> {
    return listOf<Coin>(
      Coin(R.string.coin1),
      Coin(R.string.coin2),
      Coin(R.string.coin3),
      Coin(R.string.coin4),
      Coin(R.string.coin5),
      Coin(R.string.coin6),
      Coin(R.string.coin7),
      Coin(R.string.coin8),
      Coin(R.string.coin9),
      Coin(R.string.coin10)
    )
  }
}