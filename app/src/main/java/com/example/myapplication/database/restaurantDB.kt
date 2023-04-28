package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.entity.RestaurantEtt
import com.example.myapplication.dao.RestaurantDao

@Database(entities = [RestaurantEtt::class], version = 1)
abstract class RestaurantDB: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

/*    abstract fun restaurantDao(): RestaurantDao

    companion object {
        var instance: RestaurantDB? = null

        @Synchronized
        fun getInstance(context: Context): RestaurantDB? {
            if (instance == null)
                synchronized(RestaurantDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantDB::class.java,
                        "restaurant"
                    )
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }*/
}