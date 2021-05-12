package com.ivanponomarev.presentation.details

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.view.SimpleDraweeView

import com.ivanponomarev.domain.Repo
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

        activity.supportActionBar?.title = data?.name

        if (data != null) {
            repoCardDetailsFragmentBinding.repoAvatarDetails.setImageURI(data.avatar)
            repoCardDetailsFragmentBinding.repoDescription.text = data.description
            repoCardDetailsFragmentBinding.repoLanguage.text = data.language
            repoCardDetailsFragmentBinding.repoLink.text = data.url
        }
    }

}