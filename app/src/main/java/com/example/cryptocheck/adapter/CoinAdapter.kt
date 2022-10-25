package com.example.cryptocheck.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApiClient
import com.example.cryptocheck.R
import com.example.cryptocheck.fragments.Coin_list_fragment
import com.example.cryptocheck.model.Coin

class CoinAdapter(
  private val context: Coin_list_fragment
) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinComparator()){

  val apiClient by lazy { CoinApiClient.create() }
  var coins: List<Coin> = ArrayList()

//  init { refreshCoins() }

  class CoinViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val coinName: TextView = view.findViewById(R.id.coin_name)


    val coinSymbol: TextView = view.findViewById(R.id.coin_symbol)

    val coinPrice: TextView = view.findViewById(R.id.coin_price)

    val coinChange: TextView = view.findViewById(R.id.coin_change)

    fun bind(coin: Coin) {
      Log.d("adapter_coin", coin.name)
      coinName.text = coin.name
      coinPrice.text = coin.price.toString()
      coinSymbol.text = coin.symbol
      coinChange.text = coin.percentChange1D.toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
    Log.d("adapter", "adapter_onCreateViewHolder")
    // create a new view
    val adapterLayout = LayoutInflater.from(parent.context)
      .inflate(R.layout.coin_list_item, parent, false)

    return CoinViewHolder(adapterLayout)
  }

  override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
    val item = getItem(position)
    // TBD
    // holder.textView.text = context.resources.getString(item.id)
    holder.bind(item)
  }

//  override fun getItemCount(): Int {
//    return coins.size
//  }

  fun refreshCoins() {
    // later will be changed to apiClient.getCoins()
    // coins = CoinApiClient.getCoins()
  }


  class CoinComparator : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
      return oldItem.id == newItem.id
    }
  }
}