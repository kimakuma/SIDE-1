package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEtt (
    @PrimaryKey(autoGenerate = true)
    val restaurantId: Int,

    val restaurantName: String,

    val categoryId: Int,


)