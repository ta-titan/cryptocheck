package com.example.cryptocheck.util

import com.example.cryptocheck.CoinResponse
import com.example.cryptocheck.model.Coin
import java.text.DecimalFormat


interface ApiHelper {

  companion object {
    fun convertResponseToModel( res : CoinResponse ) : List<Coin> {
      var coinList : List<Coin> = mutableListOf()
      val df = DecimalFormat("#.###")

      res.coinData?.forEach {
        coinList = coinList.plus(Coin(
          id = it.id,
          name = it.name.toString(),
          symbol = it.symbol.toString(),
          price = df.format(it.quote?.usd?.price).toDouble(),
          volume = df.format(it.quote?.usd?.volume_24h).toDouble(),
          percentChange1D = df.format(it.quote?.usd?.percent_change_24h).toDouble(),
          percentChange1H = df.format(it.quote?.usd?.percent_change_1h).toDouble(),
          percentChange1W = df.format(it.quote?.usd?.percent_change_7d).toDouble()
        ))
      }

      return coinList
    }
  }


}