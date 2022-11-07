package com.example.cryptocheck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.MainActivity
import com.example.cryptocheck.R
import com.example.cryptocheck.adapter.CoinAdapter
import com.example.cryptocheck.model.Coin

class CoinListFragment : Fragment(), CoinAdapter.CreateFragmentListener {

  override fun createFragmentListener(coin: Coin) {
    val coinFragment = CoinFragment(coin)
    requireActivity().supportFragmentManager
      .beginTransaction()
      .replace(R.id.fragmentContainerHost, coinFragment)
      .addToBackStack(null)
      .commit()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.coin_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val adapter = CoinAdapter(this, (activity as MainActivity).userViewModel)

    val refreshButton = view?.findViewById<ImageView>(R.id.refresh_coins)

    refreshButton?.setOnClickListener {
      (activity as MainActivity).updateCoinsFromApi()
    }

    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
    if (recyclerView != null) {
      recyclerView.adapter = adapter
      recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    } else {
      Log.d("coinListFragment", "Recycler view is null")
    }

    (activity as MainActivity).coinViewModel.allCoins.observe(viewLifecycleOwner) { coins ->
      coins.let { adapter.submitList(it) }
    }
  }
}