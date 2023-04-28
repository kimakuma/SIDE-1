package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import com.example.myapplication.entity.RestaurantEtt
import com.example.myapplication.dao.RestaurantDao
import com.example.myapplication.database.RestaurantDB
import kotlinx.coroutines.*
import android.util.Log
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout;
/*    private lateinit var db: restaurantDB;
    var restaurants:List<restaurantEtt> = listOf<restaurantEtt>()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // drawer
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // kimakuma] drawer icon change <- after instantiating actionbartoggle
        toolbar.setNavigationIcon(R.drawable.menu)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

        // db
        val db = Room.databaseBuilder(this, RestaurantDB::class.java,"restaurant").build()
        CoroutineScope(Dispatchers.IO).launch {
            var output = db.restaurantDao().getAll()[0]
            Log.d("db_test", "$output")
        }
    }

    // drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // db
/*    @SuppressLint("StaticFieldLeak")
    fun getAllRestaurants() {
        val getTask = (object: AsyncTask<Unit,Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                restaurants = db.restaurantDao().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(restaurants)
            }
        }).execute()
    }*/

}