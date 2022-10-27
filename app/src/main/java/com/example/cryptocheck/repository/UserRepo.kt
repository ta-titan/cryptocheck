package com.example.cryptocheck.repository

import com.example.cryptocheck.dao.UserDao
import com.example.cryptocheck.model.CurrentUser
import com.example.cryptocheck.model.User
import kotlinx.coroutines.flow.Flow

class UserRepo(private val userDao: UserDao) {

  val allUsers: Flow<List<User>> = userDao.getAllUsersFromRoomDb()
  val currentUser: Flow<CurrentUser> = userDao.getCurrentUser()

}