package com.example.cryptocheck.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptocheck.CoinApplication
import com.example.cryptocheck.R
import com.example.cryptocheck.databinding.FragmentDashboardBinding
import com.example.cryptocheck.viewmodel.UserViewModel
import com.example.cryptocheck.viewmodel.UserViewModelFactory

class Dashboard : Fragment() {

  private lateinit var binding: FragmentDashboardBinding
  private val viewModel: UserViewModel by viewModels<UserViewModel> {
    UserViewModelFactory((activity?.application as CoinApplication).userRepo)
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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.userViewModel = viewModel
//    viewModel.currentUser.value?.userName?.let { Log.d("dashboard created", it) }
    viewModel.currentUser.observe(viewLifecycleOwner, {currentUser ->
      binding.currentUser = currentUser
    })
  }
  companion object {
    @JvmStatic
    fun newInstance() = Dashboard()
  }
}