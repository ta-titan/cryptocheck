package com.example.cryptocheck.data

import com.example.cryptocheck.R
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.UserDetails

class Datasource {

  fun loadCoins(): List<Coin> {
    return listOf<Coin>(
      getCoin("Bitcoin", 1, "BTC"),
      getCoin("Binance", 2, "BIN"),
      getCoin("Etherum", 3, "ETH"),
      getCoin("Dogecoin", 4, "DGE"),
      getCoin("Tether", 5, "TTH"),
      getCoin("Litecoin", 6, "LTE"),
      getCoin("Monero", 7, "MNO"),
      getCoin("Verge", 8, "VGE"),
      getCoin("Tron", 9, "TRN"),
      getCoin("Nano", 10, "NNO")
    )
  }

  fun loadCurrentUser() : CurrentUser {
    return CurrentUser(1, emptyList(), "Tarun")
  }


  fun getCoin(name: String, id : Int, symbol : String) : Coin {
    return Coin(id, symbol, name, 1, 1, 1, 1, 1)
  }
}