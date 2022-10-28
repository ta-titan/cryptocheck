package com.example.cryptocheck.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApplication
import com.example.cryptocheck.R
import com.example.cryptocheck.adapter.CoinAdapter
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.CoinViewModel
import com.example.cryptocheck.viewmodel.CoinViewModelFactory
import com.example.cryptocheck.viewmodel.UserViewModel
import com.example.cryptocheck.viewmodel.UserViewModelFactory

class CoinListFragment : Fragment(), CoinAdapter.CreateFragmentListener {

  companion object {
  }

  private val viewModel: CoinViewModel by viewModels<CoinViewModel> {
    CoinViewModelFactory( (activity?.application as CoinApplication).repository)
  }

  private val userViewModel: UserViewModel by viewModels<UserViewModel> {
    UserViewModelFactory((activity?.application as CoinApplication).userRepo)
  }

  override fun createFragmentListener(coin: Coin) {
    val coinFragment = CoinFragment()
    activity?.supportFragmentManager
      ?.beginTransaction()
      ?.replace(R.id.flFragment, coinFragment)
      ?.addToBackStack(null)
      ?.commit()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    return inflater.inflate(R.layout.coin_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val adapter = CoinAdapter(this, userViewModel)
    val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recycler_view)
    if (recyclerView != null) {
      recyclerView.adapter = adapter
    } else {
      Log.d("coinListFragment", "Recycler view is null")
    }

    Log.d("mainactivity", "onCreate")

    viewModel.allCoins.observe( viewLifecycleOwner, Observer { coins ->
      //Log.d("mainactivity", coins.get(0).name)
      coins.let { adapter.submitList(it) }
    })

    if (recyclerView != null) {
      recyclerView.setHasFixedSize(true)
    }

    if (recyclerView != null) {
      recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL))
    }
  }

}