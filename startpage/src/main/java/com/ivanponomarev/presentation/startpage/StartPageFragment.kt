package com.ivanponomarev.presentation.startpage

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
import com.ivanponomarev.gittrends.startpage.databinding.StartPageFragmentBinding
import com.ivanponomarev.presentation.details.RepoCardDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartPageFragment @Inject constructor() : Fragment() {

    private var myContext: FragmentActivity? = null

    private val startPageViewModel: StartPageViewModel by viewModels()

    private lateinit var activityMainContentBinding: StartPageFragmentBinding

    private val startPageRecyclerViewAdapter by lazy { StartPageRecyclerViewAdapter(this::showDetailsFragment) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        activityMainContentBinding = StartPageFragmentBinding.inflate(layoutInflater)
        activityMainContentBinding.lifecycleOwner = this

        val startPageRecyclerView = activityMainContentBinding.startPageRecyclerView

        startPageRecyclerView.adapter = startPageRecyclerViewAdapter

        // Fetch data and send it to recycler view adapter
        startPageViewModel.fetchReposData()
        startPageViewModel.myResponseRepos.observe(viewLifecycleOwner, { response ->
            if (response.isEmpty().not()) {
                startPageRecyclerViewAdapter.setData(response)

            } else {
                //Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT)

            }
        })

        return activityMainContentBinding.root
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