package com.example.cryptocheck.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptocheck.databinding.CoinDetailBinding
import com.example.cryptocheck.model.Coin

class CoinFragment(val coin: Coin) : Fragment() {

  private lateinit var binding : CoinDetailBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = CoinDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  private fun bindValues() {
    binding.coin = coin

    if (coin.percentChange1D!! < 0) {
      binding.change1d.setTextColor(Color.RED)
      val resTrendingDown = context?.resources?.getIdentifier("trending_down", "drawable", "com.example.cryptocheck")
      if (resTrendingDown != null) {
        binding.change1DLogo.setImageResource(resTrendingDown)
      }
    } else {
      binding.change1d.setTextColor(Color.GREEN)
    }

    if (coin.percentChange1H!! < 0) {
      binding.change1h.setTextColor(Color.RED)
      val resTrendingDown = context?.resources?.getIdentifier("trending_down", "drawable", "com.example.cryptocheck")
      if (resTrendingDown != null) {
        binding.change1HLogo.setImageResource(resTrendingDown)
      }
    } else {
      binding.change1h.setTextColor(Color.GREEN)
    }

    if (coin.percentChange1W!! < 0) {
      binding.change1w.setTextColor(Color.RED)
      val resTrendingDown = context?.resources?.getIdentifier("trending_down", "drawable", "com.example.cryptocheck")
      if (resTrendingDown != null) {
        binding.change1WLogo.setImageResource(resTrendingDown)
      }
    } else {
      binding.change1w.setTextColor(Color.GREEN)
    }

    var resID = resources.getIdentifier(coin.symbol.lowercase(), "drawable", "com.example.cryptocheck" )
    if (resID == 0)
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