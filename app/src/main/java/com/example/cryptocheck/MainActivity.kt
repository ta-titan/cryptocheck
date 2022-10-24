package com.example.cryptocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.adapter.CoinAdapter
import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.viewmodel.CoinViewModel
import com.example.cryptocheck.viewmodel.CoinViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private val coinViewModel: CoinViewModel by viewModels {
    CoinViewModelFactory((application as CoinApplication).repository)

  }
//  private val coinViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//      if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
//        @Suppress("UNCHECKED_CAST")
//        return CoinViewModel((application as CoinApplication).repository) as T
//      }
//      throw IllegalArgumentException("Unknown ViewModel class")
//    }
//  })[CoinViewModel::class.java]

   fun initDatabase() {
     CoroutineScope(Dispatchers.IO).launch {
       // default data
       val coinDao = (application as CoinApplication).database.coinDao()
       coinDao.deleteAll()

       val coins: List<Coin> = Datasource().loadCoins()

       for (coin in coins) {
         coinDao.insert(coin)
       }
     }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val adapter = CoinAdapter(this)
    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.adapter = adapter

    Log.d("mainactivity", "onCreate")

    initDatabase()

    coinViewModel.allCoins.observe( this, Observer { coins ->
      //Log.d("mainactivity", coins.get(0).name)
      coins.let { adapter.submitList(it) }
    })

    recyclerView.setHasFixedSize(true)
  }
}