package com.ivanponomarev.presentation.repos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.ActivityMainContentFragmentBinding
import com.ivanponomarev.presentation.details.RepoCardDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private var myContext: FragmentActivity? = null

    private val startPageViewModel: StartPageViewModel by viewModels()

    private lateinit var activityMainContentBinding: ActivityMainContentFragmentBinding
    private val recyclerViewAdapter by lazy { RecyclerViewAdapter(this::showDetailsFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activityMainContentBinding = ActivityMainContentFragmentBinding.inflate(layoutInflater)
        activityMainContentBinding.lifecycleOwner = this

        val view = activityMainContentBinding.root

        val recyclerView = activityMainContentBinding.recyclerView

        recyclerView.adapter = recyclerViewAdapter

        startPageViewModel.fetchReposData()
        startPageViewModel.myResponseRepos.observe(viewLifecycleOwner, Observer { response ->
            if (response.isEmpty().not()) {
                recyclerViewAdapter.setData(response)

            } else {
                //Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT)

            }
        })

        return view
    }

    private fun showDetailsFragment(data: Repo?) {

        val transaction = myContext!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.details_fragment_container, RepoCardDetailsFragment(data))
        transaction.commit()

        transaction.addToBackStack("details")
    }

    override fun onAttach(context: Context) {

        myContext = activity as FragmentActivity

        super.onAttach(context)
    }

}