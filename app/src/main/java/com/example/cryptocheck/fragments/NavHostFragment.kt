package com.example.cryptocheck.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.cryptocheck.R
import com.example.cryptocheck.databinding.NavHostBinding

class NavHostFragment : Fragment() {

  private lateinit var binding : NavHostBinding
  private lateinit var navController: NavController

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding  = NavHostBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    navController = activity?.let { Navigation.findNavController(it, R.id.flFragment) }!!
    setupWithNavController(binding.bottomNavigationView, navController)
  }
}