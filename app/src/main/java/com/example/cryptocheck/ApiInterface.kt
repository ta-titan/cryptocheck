package com.example.cryptocheck

import com.example.cryptocheck.model.Coin
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {

  @GET("latest")
  fun getCoinsListing() : Observable<List<Coin>>

}