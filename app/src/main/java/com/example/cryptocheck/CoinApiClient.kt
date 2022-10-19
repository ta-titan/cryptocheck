package com.example.cryptocheck

import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CoinList
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface CoinApiClient {

//  @GET("coins") fun getCoins(@Header("Authorization") token:String): Observable<CoinList>

  companion object {

    fun create(): CoinApiClient {

      val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://127.0.0.1/")
        .build()

      return retrofit.create(CoinApiClient::class.java)
    }

    // will be removed later
    fun getCoins() : List<Coin> {
      return Datasource().loadCoins()
    }

  }
}