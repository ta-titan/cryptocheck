package com.example.cryptocheck.adapter

import android.app.PendingIntent.getActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApiClient
import com.example.cryptocheck.R
import com.example.cryptocheck.fragments.CoinFragment
import com.example.cryptocheck.fragments.CoinListFragment
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.UserViewModel

class CoinAdapter(
  private val context: CoinListFragment,
  private val userViewModel: UserViewModel,
) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinComparator()){

  val apiClient by lazy { CoinApiClient.create() }
  var coins: List<Coin> = ArrayList()


  public interface CreateFragmentListener {
    fun createFragmentListener(coin : Coin)
  }
//  init { refreshCoins() }

  class CoinViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val coinName: TextView = view.findViewById(R.id.coin_name)
    val coinSymbol: TextView = view.findViewById(R.id.coin_symbol)
    val coinPrice: TextView = view.findViewById(R.id.coin_price)
    val coinChange: TextView = view.findViewById(R.id.coin_change)
    val favButton: CheckBox = view.findViewById(R.id.add2Fav)

    fun bind(coin: Coin, userViewModel: UserViewModel) {
      Log.d("adapter_coin", coin.name)
      coinName.text = coin.name
      coinPrice.text = coin.price.toString()
      coinSymbol.text = coin.symbol
      coinChange.text = coin.percentChange1D.toString()
      favButton.isChecked = userViewModel.currentUserWatchList.get()?.contains(coin.id) == true
    }

    fun add2favListener(coin : Coin, userViewModel: UserViewModel) {
      favButton.setOnClickListener {
        if ( favButton.isChecked )
          userViewModel.addCoinToWatchList(coin, false)
        else
          userViewModel.addCoinToWatchList(coin, true)
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
    // TBD
    // holder.textView.text = context.resources.getString(item.id)
    holder.bind(item, userViewModel)
    holder.add2favListener(item, userViewModel)
    holder.coinNameSymbolListener(item, context)
  }

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