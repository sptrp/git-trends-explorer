package com.ivanponomarev.presentation.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.FavouritesFragmentBinding
import com.ivanponomarev.presentation.details.RepoCardDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var myContext: FragmentActivity? = null

    private lateinit var favouritesBinding: FavouritesFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        favouritesBinding = FavouritesFragmentBinding.inflate(layoutInflater)
        favouritesBinding.lifecycleOwner = this

        return favouritesBinding.root
    }

    private fun showDetailsFragment(data: Repo?) {

        val transaction = myContext!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.startPageFragment, RepoCardDetailsFragment(data))
        transaction.commit()

        transaction.addToBackStack("details")
    }

    override fun onAttach(context: Context) {

        myContext = activity as FragmentActivity

        super.onAttach(context)
    }
}