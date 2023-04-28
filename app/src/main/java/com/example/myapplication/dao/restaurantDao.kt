package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.entity.RestaurantEtt

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    suspend fun getAll(): List<RestaurantEtt>
}