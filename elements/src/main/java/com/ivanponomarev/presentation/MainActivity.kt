package com.ivanponomarev.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.ActivityMainBinding

import com.ivanponomarev.presentation.favourites.FavouritesFragment
import com.ivanponomarev.presentation.info.InfoFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding.lifecycleOwner = this
        setContentView(activityMainBinding.root)

        // Disable auto night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Fresco.initialize(this)

        // Navigation
        activityMainBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_nav_button -> {
                    // Respond to navigation item 1 click
                    Log.d("Bottom navigation", "Clicked Home")
                    if (this.supportFragmentManager.backStackEntryCount > 0) {
                        for (f in 0 until this.supportFragmentManager.backStackEntryCount) {
                            this.supportFragmentManager.popBackStack()
                        }
                    }
                    true
                }
                R.id.favorites_nav_button -> {
                    // Respond to navigation item 2 click
                    Log.d("Bottom navigation", "Clicked Favourites")
                    showFavouritesFragment()

                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu?.clear()
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                // Remove the details Fragment
                Log.d("Fragment", "removing fragment")
                this.supportFragmentManager.popBackStack()

                return true
            }

            R.id.menu_item_info -> {
                Log.d("Menu", "On info clicked")
                showInfoFragment()

                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFavouritesFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.startPageFragment, FavouritesFragment())
        transaction.commit()

        transaction.addToBackStack("favourites")
    }

    private fun showInfoFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.startPageFragment, InfoFragment())
        transaction.commit()

        transaction.addToBackStack("info")
    }

}