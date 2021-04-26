package com.ivanponomarev.presentation.details

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView

import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R

import com.ivanponomarev.presentation.repos.StartPageViewModel


class RepoCardDetailsFragment(private val data: Repo?) : Fragment() {

    private lateinit var viewModel: StartPageViewModel
    private lateinit var imageView: SimpleDraweeView

    private lateinit var descriptionTextView: TextView
    private lateinit var languageTextView: TextView
    private lateinit var linkTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.repo_card_details_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()


    }

}