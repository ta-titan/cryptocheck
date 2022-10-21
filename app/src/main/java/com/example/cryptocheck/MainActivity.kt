package com.example.cryptocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.adapter.CoinAdapter
import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.viewmodel.CoinViewModel
import com.example.cryptocheck.viewmodel.CoinViewModelFactory

class MainActivity : AppCompatActivity() {

  private val coinViewModel: CoinViewModel by viewModels {
    CoinViewModelFactory((application as CoinApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val adapter = CoinAdapter(this)
    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.adapter = adapter

    coinViewModel.allCoins.observe( this, Observer { coins ->
      coins.let { adapter.submitList(it) }
    })

    recyclerView.setHasFixedSize(true)
  }
}