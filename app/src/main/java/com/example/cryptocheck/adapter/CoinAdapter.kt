package com.example.cryptocheck.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.R
import com.example.cryptocheck.fragments.CoinListFragment
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.UserViewModel

class CoinAdapter(
  private val context: CoinListFragment,
  private val userViewModel: UserViewModel,
) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinComparator()){

  var coins: List<Coin> = ArrayList()

  interface CreateFragmentListener {
    fun createFragmentListener(coin : Coin)
  }
//  init { refreshCoins() }

  class CoinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val coinName: TextView = view.findViewById(R.id.coin_name)
    private val coinSymbol: TextView = view.findViewById(R.id.coin_symbol)
    private val coinPrice: TextView = view.findViewById(R.id.coin_price)
    private val coinChange: TextView = view.findViewById(R.id.coin_change)
    private val favButton: CheckBox = view.findViewById(R.id.add2Fav)
    private val symbolLogo : ImageView = view.findViewById(R.id.listItemSvg)

    fun bind(coin: Coin, userViewModel: UserViewModel, context: CoinListFragment) {
      Log.d("adapter_coin", coin.name)
      coinName.text = coin.name
      coinPrice.text = coin.price.toString()
      coinSymbol.text = coin.symbol
      coinChange.text = coin.percentChange1D.toString()
      favButton.isChecked = userViewModel.currentUser.value?.watchList?.contains(coin.id) == true
      Log.d("currentWatchList", userViewModel.currentUser.value?.watchList?.size.toString())
      var resID = context.resources.getIdentifier(coin.symbol.lowercase(), "drawable", "com.example.cryptocheck")
      if (resID == 0)
        resID = context.resources.getIdentifier("cob", "drawable", "com.example.cryptocheck")

      if ( coin.symbol.lowercase() == "ape")
        resID = context.resources.getIdentifier("cob", "drawable", "com.example.cryptocheck")

      symbolLogo.setImageResource(resID)
    }

    fun add2favListener(coin : Coin, userViewModel: UserViewModel) {
      favButton.setOnClickListener {
        if ( favButton.isChecked ) {
          Log.d("already_checked", coin.name)
          userViewModel.addCoinToWatchList(coin, true)
        }
        else {
          userViewModel.addCoinToWatchList(coin, false)
        }
        Log.d("Coin added to fav:", coin.name)
      }
    }


    fun coinNameSymbolListener(coin : Coin, context: CoinListFragment) {
      coinName.setOnClickListener {
        context.createFragmentListener(coin)
      }
      coinSymbol.setOnClickListener {
        context.createFragmentListener(coin)
      }
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
    holder.bind(item, userViewModel, context)
    holder.add2favListener(item, userViewModel)
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