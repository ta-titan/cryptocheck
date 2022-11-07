package com.example.cryptocheck.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.R
import com.example.cryptocheck.fragments.DashboardFragment
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.UserViewModel

class WatchListAdapter (
  private val context : DashboardFragment,
  private val userViewModel: UserViewModel,
) : ListAdapter<Coin, WatchListAdapter.WatchListItemHolder>(CoinComparator()) {

  class WatchListItemHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val coinName: TextView = view.findViewById(R.id.coin_name_wl)
    private val coinSymbol: TextView = view.findViewById(R.id.coin_symbol_wl)
    private val coinPrice: TextView = view.findViewById(R.id.coin_price_wl)
    private val coinChange: TextView = view.findViewById(R.id.coin_change_wl)
    private val symbolLogo : ImageView = view.findViewById(R.id.coin_logo_wl)
    private val deleteButton : ImageView = view.findViewById(R.id.removeFromWatchList)
    private val coinChangeSymbol : ImageView = view.findViewById(R.id.changeSymbol_wl)

    fun bind(coin: Coin, context : DashboardFragment) {
      Log.d("watchListViewHolder", coin.name)
      coinName.text = coin.name
      coinPrice.text = coin.price.toString()
      coinSymbol.text = coin.symbol
      coinChange.text = coin.percentChange1D.toString()

      if (coin.percentChange1D!! < 0) {
        coinChange.setTextColor(Color.RED)
        val resTrendingDown = context.resources.getIdentifier("trending_down", "drawable", "com.example.cryptocheck")
        coinChangeSymbol.setImageResource(resTrendingDown)
      } else {
        coinChange.setTextColor(Color.GREEN)
      }

      var resID = context.resources.getIdentifier(coin.symbol.lowercase(), "drawable", "com.example.cryptocheck")
      if (resID == 0)
        resID = context.resources.getIdentifier("cob", "drawable", "com.example.cryptocheck")

      symbolLogo.setImageResource(resID)
    }

    fun removeFromFavoriteListener(coin : Coin, userViewModel: UserViewModel) {
      deleteButton.setOnClickListener {
        userViewModel.updateWatchList(coin, false)
        Log.d("Coin removed from fav:", coin.name)
      }
    }

    fun coinNameSymbolListener(coin : Coin, context: DashboardFragment) {
      coinName.setOnClickListener {
        context.createFragmentListener(coin)
      }
      coinSymbol.setOnClickListener {
        context.createFragmentListener(coin)
      }
      symbolLogo.setOnClickListener {
        context.createFragmentListener(coin)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListItemHolder {
    val adapterLayout = LayoutInflater.from(parent.context)
      .inflate(R.layout.watch_list_item, parent, false)
    Log.d("wl_adapter", "watch list adapter created ")
    return WatchListItemHolder(adapterLayout)
  }

  override fun onBindViewHolder(holder: WatchListItemHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item, context)
    holder.removeFromFavoriteListener(item, userViewModel)
    holder.coinNameSymbolListener(item, context)
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