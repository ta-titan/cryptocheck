package com.example.cryptocheck.data

import com.example.cryptocheck.model.CurrentUser

class Datasource {

  fun loadCurrentUser() : CurrentUser {
    return CurrentUser(1, emptyList(), "Tarun")
  }
}