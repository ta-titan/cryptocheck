package com.example.cryptocheck.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cryptocheck.dao.UserDao
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.User
import kotlinx.coroutines.flow.Flow

class UserRepo(private val userDao: UserDao) {

  val allUsers: Flow<List<User>> = userDao.getAllUsersFromRoomDb()

  val currentUser: LiveData<CurrentUser> = userDao.getCurrentUser().asLiveData()

}