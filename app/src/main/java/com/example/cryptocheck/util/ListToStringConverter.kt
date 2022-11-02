package com.example.cryptocheck.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListToStringConverter {

  @TypeConverter
  fun fromStringArrayList(value: List<String>): String {
    return Gson().toJson(value)
  }

  @TypeConverter
  fun toStringArrayList(value: String): List<String> {
    return try {
      val listType: Type = object : TypeToken<List<String?>?>() {}.type
      Gson().fromJson(value, listType) //using extension function
    } catch (e: Exception) {
      arrayListOf()
    }
  }

}