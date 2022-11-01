package com.example.cryptocheck.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptocheck.R
import com.example.cryptocheck.databinding.CoinDetailBinding
import com.example.cryptocheck.model.Coin

class CoinFragment(coin: Coin) : Fragment() {

  var coin = coin
  private lateinit var binding : CoinDetailBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = CoinDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  fun bindValues() {
    binding.setCoinName(coin.name)
    binding.setCoinPrice(coin.price.toString())
    binding.setCoinSymbol(coin.symbol)
    binding.coinVolume = coin.volume.toString()
    binding.setChange1d(coin.percentChange1D.toString())
    binding.setChange1h(coin.percentChange1H.toString())
    binding.setChange1w(coin.percentChange1W.toString())
    var resID = resources.getIdentifier(coin.symbol.lowercase(), "drawable", "com.example.cryptocheck" )
    if ( resID == null || resID == 0)
      resID = resources.getIdentifier("cob", "drawable", "com.example.cryptocheck")
    binding.coinLogo.setImageResource(resID)
    binding.back2home.setOnClickListener {
      activity?.supportFragmentManager?.popBackStack()
    }
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    bindValues()
  }
}