package com.example.cryptocheck

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoinResponse {

  @SerializedName("data")
  @Expose

  val coinData: Set<CoinResponseElement>? = null

  class CoinResponseElement {

    @SerializedName("id")
    @Expose
    lateinit var id: String

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

    @SerializedName("quote")
    @Expose
    var quote: Quote? = null
  }


  class Quote {
    @SerializedName("USD")
    @Expose
    var usd: USD? = null
  }

  class USD {
    @SerializedName("price")
    @Expose
    var price: Double? = 0.0

    @SerializedName("volume_24h")
    @Expose
    var volume24h: Double? = 0.0

    @SerializedName("percent_change_1h")
    @Expose
    var percentChange1h: Double = 0.0

    @SerializedName("percent_change_24h")
    @Expose
    var percentChange24h: Double? = 0.0

    @SerializedName("percent_change_7d")
    @Expose
    var percentChange7d: Double? = 0.0

  }
}