package com.example.cryptocheck.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.CoinApplication
import com.example.cryptocheck.MainActivity
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

  fun getWatchListFromIds(ids : List<String>) : List<Coin> {
    var watchList : List<Coin> = mutableListOf()
    val allCoins = (activity as MainActivity).coinViewModel.allCoins.value

    Log.d("dashboardAllCoins", allCoins?.size.toString())
    allCoins?.filter { ids.contains(it.id) }?.forEach { watchList = watchList.plus(it) }

    Log.d("dashboardWatchlist", watchList.size.toString())
    return watchList
  }

  fun setNewUsername( name : String ) {
    (activity as MainActivity).userViewModel.updateUsername(name)
  }

  fun getusernameDialog() {
    val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this.context)
    builder.setTitle("Username")

    val input = EditText(this.context)
    input.setHint("Enter new username")
    input.inputType = InputType.TYPE_CLASS_TEXT
    builder.setView(input)

    builder.setPositiveButton("Submit") { dialog, which ->
      var m_Text = input.text.toString()
      setNewUsername(m_Text)
    }
    builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

    builder.show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.userViewModel = (activity as MainActivity).userViewModel


    val adapter = WatchListAdapter(this)
    val recyclerView = getView()?.findViewById<RecyclerView>(R.id.userWatchList)

    recyclerView?.adapter = adapter
    recyclerView?.setHasFixedSize(true)
    recyclerView?.addItemDecoration(DividerItemDecoration(
      recyclerView.getContext(),
      DividerItemDecoration.VERTICAL
    ))

    (activity as MainActivity).userViewModel.currentUser.observe(viewLifecycleOwner) {
      adapter.submitList(getWatchListFromIds(it.watchList))
      (activity as MainActivity).userViewModel.currentUserName.set(it.userName)
    }


    binding.editUsername.setOnClickListener{
      getusernameDialog()
    }

  }
  companion object {
    @JvmStatic
    fun newInstance() = Dashboard()
  }
}