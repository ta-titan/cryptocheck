package com.example.cryptocheck.data

import com.example.cryptocheck.R
import com.example.cryptocheck.model.Coin

class Datasource {

  fun loadCoins(): List<Coin> {
    return listOf<Coin>(
      getCoin("Bitcoin", 1),
      getCoin("Binance", 2),
      getCoin("Etherum", 3),
      getCoin("Dogecoin", 4),
      getCoin("Tether", 5),
      getCoin("Litecoin", 6),
      getCoin("Monero", 7),
      getCoin("Verge", 8),
      getCoin("Tron", 9),
      getCoin("Nano", 10)
    )
  }

  fun getCoin(name: String, id : Int) : Coin {
    return Coin(id, "", name, 1, 1, 1, 1, 1)
  }
}