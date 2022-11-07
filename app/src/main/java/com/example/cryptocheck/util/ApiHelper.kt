package com.example.cryptocheck.util

import com.example.cryptocheck.CoinResponse
import com.example.cryptocheck.model.Coin
import java.text.DecimalFormat


interface ApiHelper {

  companion object {
    fun convertResponseToModel( res : CoinResponse ) : List<Coin> {
      val df = DecimalFormat("#.###")

      val coinList = res.coinData?.map {
        Coin(
          id = it.id,
          name = it.name.toString(),
          symbol = it.symbol.toString(),
          price = df.format(it.quote?.usd?.price).toDouble(),
          volume = df.format(it.quote?.usd?.volume24h).toDouble(),
          percentChange1D = df.format(it.quote?.usd?.percentChange24h).toDouble(),
          percentChange1H = df.format(it.quote?.usd?.percentChange1h).toDouble(),
          percentChange1W = df.format(it.quote?.usd?.percentChange7d).toDouble()
        )
      } ?: emptyList()
      return coinList
    }
  }
}