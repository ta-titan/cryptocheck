package com.example.cryptocheck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApiClient
import com.example.cryptocheck.R
import com.example.cryptocheck.model.Coin

class CoinAdapter(
  private val context: Context
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(){

  val apiClient by lazy { CoinApiClient.create() }
  var coins: List<Coin> = ArrayList()

  init { refreshCoins() }

  class CoinViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.coin_title)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
    // create a new view
    val adapterLayout = LayoutInflater.from(parent.context)
      .inflate(R.layout.coin_list_item, parent, false)

    return CoinViewHolder(adapterLayout)
  }

  override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
    val item = coins[position]
    holder.textView.text = context.resources.getString(item.stringResourceId)
  }

  override fun getItemCount(): Int {
    return coins.size
  }

  fun refreshCoins() {
    coins = CoinApiClient.getCoins()
  }

}