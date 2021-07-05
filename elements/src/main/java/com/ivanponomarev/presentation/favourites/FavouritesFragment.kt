package com.ivanponomarev.presentation.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
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

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private val favouritesRecyclerViewAdapter by lazy { FavouritesRecyclerViewAdapter(this::showDetailsFragment) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        favouritesBinding = FavouritesFragmentBinding.inflate(layoutInflater)
        favouritesBinding.lifecycleOwner = this

        val favouritesRecyclerView = favouritesBinding.favouritesRecyclerView

        favouritesRecyclerView.adapter = favouritesRecyclerViewAdapter

        setHasOptionsMenu(true)

        // Fetch data and send it to recycler view adapter
        favouritesViewModel.fetchFavouritesData()
        favouritesViewModel.favourites.observe(viewLifecycleOwner, { response ->
            if (response.isEmpty().not()) {
                favouritesRecyclerViewAdapter.setData(response)

            } else {
                //Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT)

            }
        })

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.menu_item_info)?.isVisible = false
    }

}