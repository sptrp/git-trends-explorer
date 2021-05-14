package com.ivanponomarev.presentation.details

import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.RepoCardDetailsFragmentBinding

import com.ivanponomarev.presentation.repos.StartPageViewModel


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

    override fun onResume() {
        super.onResume()

        val supportActionBar = repoCardDetailsFragmentBinding.reposDetailsFragmentToolbar
        val activity = activity as AppCompatActivity

        activity.setSupportActionBar(supportActionBar)

        // Display home button as back arrow
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)

        val titleText = SpannableString(data?.name)
        titleText.setSpan(ForegroundColorSpan(Color.BLACK), 0, titleText.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE) // Set title color black
        activity.supportActionBar?.title = titleText

        if (data != null) {
            repoCardDetailsFragmentBinding.repoAvatarDetails.setImageURI(data.avatar)
            repoCardDetailsFragmentBinding.repoDescription.text = data.description
            repoCardDetailsFragmentBinding.repoLanguage.text = data.language
            repoCardDetailsFragmentBinding.repoLink.text = data.url
        }
    }

}