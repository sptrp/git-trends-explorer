package com.ivanponomarev.presentation.info

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.InfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var infoFragmentBinding: InfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        infoFragmentBinding = InfoFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return infoFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()

        // Set support action bar to get three dots menu
        val activity = activity as AppCompatActivity
        val supportActionBar = infoFragmentBinding.infoFragmentToolbar

        activity.setSupportActionBar(supportActionBar)

        val titleText = SpannableString(getString(R.string.info_title))
        titleText.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            titleText.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        activity.supportActionBar?.title = titleText

        activity.setSupportActionBar(supportActionBar)
    }

}