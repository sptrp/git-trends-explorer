package com.ivanponomarev.presentation.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.RepoCardDetailsFragmentBinding
import com.ivanponomarev.util.toJson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class RepoCardDetailsFragment(private val data: Repo?) : Fragment() {

    private lateinit var repoCardDetailsFragmentBinding: RepoCardDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        repoCardDetailsFragmentBinding = RepoCardDetailsFragmentBinding.inflate(inflater, container, false)

        return repoCardDetailsFragmentBinding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()

        val activity = activity as AppCompatActivity
        val supportActionBar = repoCardDetailsFragmentBinding.reposDetailsFragmentToolbar
        val favButton = repoCardDetailsFragmentBinding.favoritesButton

        activity.setSupportActionBar(supportActionBar)

        // Display home button as back arrow
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)

        val titleText = SpannableString(data?.name)
        titleText.setSpan(ForegroundColorSpan(Color.BLACK), 0, titleText.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE) // Set title color black
        activity.supportActionBar?.title = titleText

        // Get shared preferences
        //val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        if (data != null) {
            repoCardDetailsFragmentBinding.repoAvatarDetails.setImageURI(data.avatar)
            repoCardDetailsFragmentBinding.repoDescription.text = data.description
            repoCardDetailsFragmentBinding.repoLanguage.text = data.language
            repoCardDetailsFragmentBinding.repoLink.text = data.url
        }

        favButton.setOnClickListener {

            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            val key = "item_" + data?.name

            with (sharedPref.edit()) {
                putString(key, data.toJson())
                apply()
                Log.d("Favlistener", key)
                Log.d("Favlistener", sharedPref.all.size.toString())
                Toast.makeText(context, "Added " + data?.name + " to favorites", Toast.LENGTH_SHORT).show()
            }
        }

        favButton.setOnTouchListener(View.OnTouchListener { v, event ->

            if (event.action == MotionEvent.ACTION_BUTTON_RELEASE) {
                favButton.setBackgroundColor(Color.BLACK)

            } else if (event.action == MotionEvent.ACTION_BUTTON_PRESS) {
                favButton.setBackgroundColor(Color.RED);
            }
            return@OnTouchListener true
        })
    }

}
