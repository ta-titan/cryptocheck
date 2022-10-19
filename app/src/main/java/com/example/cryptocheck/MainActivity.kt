package com.example.cryptocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocheck.adapter.CoinAdapter
import com.example.cryptocheck.data.Datasource

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.adapter = CoinAdapter(this)

    recyclerView.setHasFixedSize(true)
  }
}