package com.example.cryptocheck.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.MainActivity
import com.example.cryptocheck.R
import com.example.cryptocheck.adapter.WatchListAdapter
import com.example.cryptocheck.databinding.FragmentDashboardBinding
import com.example.cryptocheck.model.Coin

class DashboardFragment : Fragment() {
  private lateinit var binding: FragmentDashboardBinding

  fun createFragmentListener(coin: Coin) {
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
  ): View {
    binding = FragmentDashboardBinding.inflate(inflater, container, false)
    return binding.root
  }

  private fun getWatchListFromIds(ids : List<String>) : List<Coin> {
    var watchList : List<Coin> = mutableListOf()
    val allCoins = (activity as MainActivity).coinViewModel.allCoins.value

    Log.d("dashboardAllCoins", allCoins?.size.toString())
    allCoins?.filter { ids.contains(it.id) }?.forEach { watchList = watchList.plus(it) }

    Log.d("dashboardWatchlist", watchList.size.toString())
    return watchList
  }

  private fun setNewUsername(name : String ) {
    (activity as MainActivity).userViewModel.updateUsername(name)
  }

  private fun getUsernameDialog() {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
    builder.setTitle("Username")

    val input = EditText(this.context)
    input.hint = "Enter new username"
    input.inputType = InputType.TYPE_CLASS_TEXT
    builder.setView(input)

    builder.setPositiveButton("Submit") { _, _ ->
      val mText = input.text.toString()
      setNewUsername(mText)
    }
    builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

    builder.show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.userViewModel = (activity as MainActivity).userViewModel

    val adapter = WatchListAdapter(this, (activity as MainActivity).userViewModel)
    val recyclerView = getView()?.findViewById<RecyclerView>(R.id.userWatchList)

    recyclerView?.adapter = adapter
    recyclerView?.addItemDecoration(DividerItemDecoration(
      recyclerView.context,
      DividerItemDecoration.VERTICAL
    ))

    (activity as MainActivity).userViewModel.currentUser.observe(viewLifecycleOwner) {
      adapter.submitList(getWatchListFromIds(it.watchList))
      (activity as MainActivity).userViewModel.currentUserName.set(it.userName)
    }

    binding.editUsername.setOnClickListener{
      getUsernameDialog()
    }
  }
}