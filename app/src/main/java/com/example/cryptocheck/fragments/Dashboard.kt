package com.example.cryptocheck.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApplication
import com.example.cryptocheck.R
import com.example.cryptocheck.adapter.WatchListAdapter
import com.example.cryptocheck.databinding.FragmentDashboardBinding
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.CoinViewModel
import com.example.cryptocheck.viewmodel.CoinViewModelFactory
import com.example.cryptocheck.viewmodel.UserViewModel
import com.example.cryptocheck.viewmodel.UserViewModelFactory

class Dashboard : Fragment() {

  private lateinit var binding: FragmentDashboardBinding
  private val viewModel: UserViewModel by viewModels<UserViewModel> {
    UserViewModelFactory((activity?.application as CoinApplication).userRepo)
  }
  private val coinViewModel: CoinViewModel by viewModels<CoinViewModel> {
    CoinViewModelFactory((activity?.application as CoinApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    binding = FragmentDashboardBinding.inflate(inflater, container, false)
    return binding.root
  }

  fun getWatchListFromIds(ids : List<Int>) : List<Coin> {
    var watchList : List<Coin> = mutableListOf()
    val allCoins = coinViewModel.allCoins.value

    allCoins?.filter {  ids.contains(it.id) }?.forEach { watchList.plus(it) }

    return watchList
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.userViewModel = viewModel
//    viewModel.currentUser.value?.userName?.let { Log.d("dashboard created", it) }
    viewModel.syncUserViewModel()

    val adapter = WatchListAdapter(this)
    val recyclerView = getView()?.findViewById<RecyclerView>(R.id.userWatchList)

    adapter.submitList(viewModel.currentUserWatchList.get()?.let { getWatchListFromIds(it) })

    recyclerView?.adapter = adapter
    recyclerView?.setHasFixedSize(true)
    recyclerView?.addItemDecoration(DividerItemDecoration(
      recyclerView.getContext(),
      DividerItemDecoration.VERTICAL
    ))

  }
  companion object {
    @JvmStatic
    fun newInstance() = Dashboard()
  }
}