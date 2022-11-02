package com.example.cryptocheck

import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CoinList
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface CoinApiClient {

//  @GET("coins") fun getCoins(@Header("Authorization") token:String): Observable<CoinList>



  companion object {

    val baseUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/"

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