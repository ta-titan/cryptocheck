package com.example.cryptocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.databinding.ActivityMainBinding
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController

   fun initDatabase() {
     CoroutineScope(Dispatchers.IO).launch {
       // default data
       val coinDao = (application as CoinApplication).database.coinDao()
       val userDao = (application as CoinApplication).database.userDao()
       coinDao.deleteAll()

       val coins: List<Coin> = Datasource().loadCoins()
       val currentUser : CurrentUser = Datasource().loadCurrentUser()

       for (coin in coins) {
         coinDao.insert(coin)
       }
       userDao.deleteCurrentUser()
       userDao.insertCurrentUser(currentUser)
     }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding= ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

//    navController = Navigation.findNavController(this, R.id.flFragment)
    navController = (supportFragmentManager.findFragmentById(R.id.flFragment) as NavHostFragment).navController
    setupWithNavController(binding.bottomNavigationView, navController)

    initDatabase()
  }
}