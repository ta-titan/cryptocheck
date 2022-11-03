package com.example.cryptocheck

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

  @Headers("X-CMC_PRO_API_KEY: ce86777b-dad7-43b6-9742-1d70020bea46")
  @GET("latest")
  fun getCoinsListing() : Observable<CoinResponse>

}