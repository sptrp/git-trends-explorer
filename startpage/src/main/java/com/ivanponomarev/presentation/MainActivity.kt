package com.ivanponomarev.presentation

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco
import com.ivanponomarev.domain.PrefManager
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.ActivityMainBinding
import com.ivanponomarev.presentation.favourites.FavouritesFragment
import com.ivanponomarev.presentation.favourites.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var sharedPrefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding.lifecycleOwner = this
        setContentView(activityMainBinding.root)

        // Disable auto night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Fresco.initialize(this)

        activityMainBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_nav_button -> {
                    // Respond to navigation item 1 click
                    Log.d("Bottom navigation", "Clicked Home")
                    this.supportFragmentManager.popBackStack()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                // Remove the details Fragment
                Log.d("Fragment", "removing fragment")
                this.supportFragmentManager.popBackStack()

                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFavouritesFragment() {

        sharedPrefManager = PrefManager(this)

        val transaction = supportFragmentManager.beginTransaction()
        /*transaction.replace(R.id.startPageFragment, FavouritesFragment())*/
        transaction.replace(R.id.startPageFragment, FavouritesFragment())
        transaction.commit()

        transaction.addToBackStack("favourites")
    }


}