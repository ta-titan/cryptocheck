package com.example.cryptocheck

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface CoinApiClient {

//  @GET("coins") fun getCoins(@Header("Authorization") token:String): Observable<CoinList>



  companion object {

    private const val baseUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/"

    fun create(): ApiInterface {

      val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build()

      return retrofit.create(ApiInterface::class.java)
    }


  }
}