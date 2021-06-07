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
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivanponomarev.domain.PrefManager

import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.databinding.RepoCardDetailsFragmentBinding
import com.ivanponomarev.util.toJson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoCardDetailsFragment @Inject constructor(
        private val data: Repo?
) : Fragment() {

    private lateinit var repoCardDetailsFragmentBinding: RepoCardDetailsFragmentBinding

    @Inject
    lateinit var sharedPrefManager: PrefManager

    private lateinit var favButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        repoCardDetailsFragmentBinding = RepoCardDetailsFragmentBinding.inflate(inflater, container, false)

        favButton = repoCardDetailsFragmentBinding.favoritesButton

        return repoCardDetailsFragmentBinding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()

        val activity = activity as AppCompatActivity
        val supportActionBar = repoCardDetailsFragmentBinding.reposDetailsFragmentToolbar

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favButton.setOnClickListener {

            //val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
            val key = "item_" + data?.name

            Log.d("favourites", key)
            Toast.makeText(context, "Added " + data?.name + " to favorites", Toast.LENGTH_SHORT).show()
            sharedPrefManager.item = data.toJson()

            return@setOnClickListener
        }

    }

}
