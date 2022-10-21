package com.example.cryptocheck.data

import com.example.cryptocheck.R
import com.example.cryptocheck.model.Coin

class Datasource {

  fun loadCoins(): List<Coin> {
    return listOf<Coin>(
      getCoin("", 1),
      getCoin("", 2),
      getCoin("", 3),
      getCoin("", 4),
      getCoin("", 5),
      getCoin("", 6),
      getCoin("", 7),
      getCoin("", 8),
      getCoin("", 9),
      getCoin("", 10)
    )
  }

  fun getCoin(name: String, id : Int) : Coin {
    return Coin(id, "", name, 1, 1, 1, 1, 1)
  }
}