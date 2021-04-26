package com.ivanponomarev.presentation

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.facebook.drawee.backends.pipeline.Fresco
import com.ivanponomarev.gittrends.startpage.databinding.ActivityMainBinding
import com.ivanponomarev.presentation.repos.StartPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val startPageViewModel: StartPageViewModel by viewModels()

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding.lifecycleOwner = this
        setContentView(activityMainBinding.root)

        Fresco.initialize(this)

        //mainViewModel.title.observe(this, Observer {
          //  Log.d("Title", "Setting title")
            //supportActionBar?.title = it
        //})
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

}