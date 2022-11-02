package com.example.cryptocheck

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.cryptocheck.data.Datasource
import com.example.cryptocheck.databinding.ActivityMainBinding
import com.example.cryptocheck.model.Coin
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.util.ApiHelper
import com.example.cryptocheck.util.DbHelper
import com.example.cryptocheck.viewmodel.CoinViewModel
import com.example.cryptocheck.viewmodel.CoinViewModelFactory
import com.example.cryptocheck.viewmodel.UserViewModel
import com.example.cryptocheck.viewmodel.UserViewModelFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  public val userViewModel: UserViewModel by viewModels<UserViewModel> {
    UserViewModelFactory((application as CoinApplication).userRepo)
  }
  public val coinViewModel: CoinViewModel by viewModels<CoinViewModel> {
    CoinViewModelFactory((application as CoinApplication).repository)
  }

   fun updateDatabase(coins : List<Coin>) {
     CoroutineScope(Dispatchers.IO).launch {
       // default data
       val coinDao = (application as CoinApplication).database.coinDao()
       val userDao = (application as CoinApplication).database.userDao()
       coinDao.deleteAll()
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


    val navHostFragment = com.example.cryptocheck.fragments.NavHostFragment()
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.flFragmentHost, navHostFragment)
      .commit()



    val apiClient by lazy { CoinApiClient.create() }
     val data = apiClient.getCoinsListing()
    data.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
      .subscribe { coins : CoinResponse ->
        var coinsDecoded = ApiHelper.Companion.convertResponseToModel(coins)
        Log.d("apiCall", coinsDecoded.size.toString())
        updateDatabase(coinsDecoded)
      }

  }
}