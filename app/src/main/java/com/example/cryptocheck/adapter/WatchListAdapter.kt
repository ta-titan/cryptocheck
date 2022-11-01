package com.example.cryptocheck.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.R
import com.example.cryptocheck.fragments.Dashboard
import com.example.cryptocheck.model.Coin

class WatchListAdapter (
  private val context : Dashboard,
) : ListAdapter<Coin, WatchListAdapter.WatchListItemHolder>(CoinComparator()) {

  class WatchListItemHolder(private val view : View) : RecyclerView.ViewHolder(view) {
    val coinName: TextView = view.findViewById(R.id.coin_name_wl)
    val coinSymbol: TextView = view.findViewById(R.id.coin_symbol_wl)
    val coinPrice: TextView = view.findViewById(R.id.coin_price_wl)
    val coinChange: TextView = view.findViewById(R.id.coin_change_wl)

    fun bind(coin: Coin) {
      Log.d("watchListViewHolder", coin.name)
      coinName.text = coin.name
      coinPrice.text = coin.price.toString()
      coinSymbol.text = coin.symbol
      coinChange.text = coin.percentChange1D.toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListItemHolder {
    var adapterLayout = LayoutInflater.from(parent.context)
      .inflate(R.layout.watch_list_item, parent, false)

    Log.d("wl_adapter", "watch list adapter created ")
    return WatchListItemHolder(adapterLayout)
  }

  override fun onBindViewHolder(holder: WatchListItemHolder, position: Int) {
    val item = getItem(position)

    holder.bind(item)
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